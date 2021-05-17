import html.*
import http.sendAndProcessResult
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.dom.clear
import kotlinx.html.dom.create
import kotlinx.html.js.a
import kotlinx.html.js.option
import collectors.collectMakefileInfoToJson
import collectors.collectSpecfileInfoToJson
import collectors.collectTargetsToJson
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDataListElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Json

abstract class PageLoader {
    abstract fun load()

    protected fun onPreview(data: Json, href: String) {
        val http = XMLHttpRequest()
        http.sendAndProcessResult("POST", href, data) {
            previewTextArea.value = http.responseText
            previewTextArea.style.height = "5px"
            previewTextArea.style.height = "${previewTextArea.scrollHeight}px"
            null
        }
    }

    protected fun onDownload(data: Json, href: String, defaultName: String) {
        val http = XMLHttpRequest()
        http.sendAndProcessResult("POST", href, data) {
            val blob = Blob(arrayOf(http.responseText), BlobPropertyBag("application/octet-stream"))
            val url = URL.createObjectURL(blob)
            try {
                val name = window.prompt("Input name:", defaultName) ?: defaultName
                document.create.a(url).apply { download = name }.click()
            } finally {
                URL.revokeObjectURL(url)
            }
        }
    }

    protected fun onClearAll() = window.location.reload()
}

class MakefilePageLoader : PageLoader() {
    private fun onAddTarget() {
        val target = document.create.createMakefileTarget()
        target.getChildById<HTMLAnchorElement>("delete-target")
            .addEventListener("click", { target.remove() })
        targetsDiv.append(target)

        // Auto-completion in dependencies
        val depsList = targetsDiv.getChildById<HTMLDataListElement>("dependencies-list")
        val dependencies = target.getChildById<HTMLInputElement>("dependencies")
        dependencies.onfocus = {
            depsList.clear()
            collectTargetsToJson().forEach deps@{ t ->
                val name = t["name"].toString()
                if (name.isBlank() ||
                    name == target.getChildById<HTMLInputElement>("name").value
                )
                    return@deps
                depsList.append(document.create.option { value = t["name"].toString() })
            }
        }
    }

    private fun onAddVariable() {
        val variable = document.create.createMakefileVariable()
        variable.getChildById<HTMLAnchorElement>("delete-variable")
            .addEventListener("click", { variable.remove() })
        variablesDiv.append(variable)
    }

    private fun onPreview() =
        onPreview(collectMakefileInfoToJson(), "/previewMakefile")

    private fun onDownload() =
        onDownload(collectMakefileInfoToJson(), "/downloadMakefile", "Makefile")

    override fun load() {
        onAddTarget()
        addVariableButton.addEventListener("click", { onAddVariable() })
        addTargetButton.addEventListener("click", { onAddTarget() })
        previewButton.addEventListener("click", { onPreview() })
        downloadButton.addEventListener("click", { onDownload() })
        clearAllButton.addEventListener("click", { onClearAll() })
    }
}

class SpecfilePageLoader : PageLoader() {
    private fun onAddDefine() {
        val define = document.create.createSpecfileDefine()
        define.getChildById<HTMLAnchorElement>("delete-define")
            .addEventListener("click", { define.remove() })
        definesDiv.append(define)
    }

    private fun onAddField(extra: Boolean = true, name: String = "", value: String = "") {
        val field = document.create.createSpecfileField(extra)
        if (extra) {
            field.getChildById<HTMLAnchorElement>("delete-field")
                .addEventListener("click", { field.remove() })
            extraFieldsDiv.append(field)
        } else {
            fieldsDiv.append(field)
            field.getChildById<HTMLInputElement>("name").value = name
            field.getChildById<HTMLInputElement>("value").value = value
        }
    }

    private fun onAddSection(extra: Boolean = true, name: String = "", body: String = "") {
        val section = document.create.createSpecfileSection(extra)
        if (extra) {
            section.getChildById<HTMLAnchorElement>("delete-section")
                .addEventListener("click", { section.remove() })
            extraSectionsDiv.append(section)
        } else {
            sectionsDiv.append(section)
            section.getChildById<HTMLInputElement>("name").value = name
            section.getChildById<HTMLInputElement>("body").value = body
        }
    }

    private fun onPreview() =
        onPreview(collectSpecfileInfoToJson(), "/previewSpecfile")

    private fun onDownload() =
        onDownload(collectSpecfileInfoToJson(), "/downloadSpecfile", "project.spec")

    private val requiredFields = setOf(
        "Name",
        "Version",
        "Release"
    )

    private val requiredSections = mapOf(
        "prep" to "setup -q",
        "build" to "",
        "install" to ""
    )

    override fun load() {
        requiredFields.forEach { onAddField(false, it) }
        requiredSections.forEach { onAddSection(false, it.key, it.value) }
        addDefineButton.addEventListener("click", { onAddDefine() })
        addFieldButton.addEventListener("click", { onAddField() })
        addSectionButton.addEventListener("click", { onAddSection() })
        previewButton.addEventListener("click", { onPreview() })
        downloadButton.addEventListener("click", { onDownload() })
        clearAllButton.addEventListener("click", { onClearAll() })
    }
}

fun main() {
    val loader = when (window.location.pathname) {
        "/createMakefile" -> MakefilePageLoader()
        "/createSpecfile" -> SpecfilePageLoader()
        else -> throw Exception()
    }
    window.onload = { loader.load() }
}