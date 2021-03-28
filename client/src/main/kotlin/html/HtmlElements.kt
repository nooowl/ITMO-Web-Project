package html

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLTextAreaElement

val targetsDiv
    get() = document.getElementById("targets") as HTMLDivElement

val variablesDiv
    get() = document.getElementById("variables") as HTMLDivElement

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