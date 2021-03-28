import html.*
import http.sendAndProcessResult
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.dom.create
import kotlinx.html.js.a
import makefile.collectMakefileInfoToJson
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import org.w3c.xhr.XMLHttpRequest

fun onAddTarget() {
    val target = document.create.createTarget()
    target.getChildById<HTMLAnchorElement>("delete-target")
        .addEventListener("click", { target.remove() })
    targetsDiv.append(target)
}

fun onAddVariable() {
    val variable = document.create.createVariable()
    variable.getChildById<HTMLAnchorElement>("delete-variable")
        .addEventListener("click", { variable.remove() })
    variablesDiv.append(variable)
}

fun onPreview() {
    val data = collectMakefileInfoToJson()

    val http = XMLHttpRequest()
    http.sendAndProcessResult("POST", "/previewMakefile", data) {
        previewTextArea.value = http.responseText
        previewTextArea.style.height = "5px"
        previewTextArea.style.height = "${previewTextArea.scrollHeight}px"
        null
    }
}

fun onDownload() {
    val data = collectMakefileInfoToJson()

    val http = XMLHttpRequest()
    http.sendAndProcessResult("POST", "/downloadMakefile", data) {
        val blob = Blob(arrayOf(http.responseText), BlobPropertyBag("application/octet-stream"))
        val url = URL.createObjectURL(blob)
        try {
            document.create.a(url).apply { download = "Makefile" }.click()
        } finally {
            URL.revokeObjectURL(url)
        }
    }
}

fun main() {
    window.onload = {
        onAddTarget()
        addVariableButton.addEventListener("click", { onAddVariable() })
        addTargetButton.addEventListener("click", { onAddTarget() })
        previewButton.addEventListener("click", { onPreview() })
        downloadButton.addEventListener("click", { onDownload() })
    }
}