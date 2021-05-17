package html

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLTextAreaElement

val targetsDiv
    get() = document.getElementById("targets") as HTMLDivElement

val variablesDiv
    get() = document.getElementById("variables") as HTMLDivElement

val definesDiv
    get() = document.getElementById("defines") as HTMLDivElement

val fieldsDiv
    get() = document.getElementById("fields") as HTMLDivElement

val sectionsDiv
    get() = document.getElementById("sections") as HTMLDivElement

val extraFieldsDiv
    get() = document.getElementById("extra-fields") as HTMLDivElement

val extraSectionsDiv
    get() = document.getElementById("extra-sections") as HTMLDivElement

val previewTextArea
    get() = document.getElementById("preview") as HTMLTextAreaElement

val previewButton
    get() = document.getElementById("preview-button") as HTMLAnchorElement

val downloadButton
    get() = document.getElementById("download-button") as HTMLAnchorElement

val addTargetButton
    get() = document.getElementById("add-target-button") as HTMLAnchorElement

val addVariableButton
    get() = document.getElementById("add-variable-button") as HTMLAnchorElement

val addDefineButton
    get() = document.getElementById("add-define-button") as HTMLAnchorElement

val addFieldButton
    get() = document.getElementById("add-field-button") as HTMLAnchorElement

val addSectionButton
    get() = document.getElementById("add-section-button") as HTMLAnchorElement

val clearAllButton
    get() = document.getElementById("clear-all") as HTMLAnchorElement