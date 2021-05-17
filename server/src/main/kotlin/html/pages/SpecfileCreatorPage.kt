package html.pages

import html.baseBody
import html.baseHead
import html.createButton
import html.titleAndNavigation
import kotlinx.html.*

val fieldsList = setOf(
    "Summary",
    "License",
    "Group",
    "Buildroot",
    "Source",
    "Requires",
    "BuildRequires"
)

val sectionsList = setOf(
    "files",
    "description",
    "changelog"
)

fun HTML.specfileCreatorPage() {
    baseHead("Create Specfile", "style", "client")
    baseBody("/createSpecfile", {
        createButton("add-define-button", "Add define")
        createButton("add-field-button", "Add field")
        createButton("add-section-button", "Add section")
        createButton("preview-button", "Update preview")
        createButton("download-button", "Download Specfile")
        createButton("clear-all", "Clear All")
    }) {
        div("creator main-flex") {
            h3 { label { +"Defines:" } }
            div("defines") {
                id = "defines"
            }
            h3 { label { +"Fields:" } }
            div("fields") {
                id = "fields"
            }
            h3 { label { +"Extra fields:" } }
            div("extra-fields") {
                id = "extra-fields"
            }
            dataList {
                id = "fields-list"
                fieldsList.forEach { option { value = it } }
            }
            div("sections") {
                id = "sections"
                h3 { label { +"Sections:" } }
            }
            div("extra-sections") {
                id = "extra-sections"
                h3 { label { +"Extra sections:" } }
            }
            dataList {
                id = "sections-list"
                sectionsList.forEach { option { value = it } }
            }
        }
        div("preview main-flex") {
            h3 {
                label {
                    htmlFor = "preview"
                    +"Preview:"
                }
            }
            textArea {
                id = "preview"
                disabled = true
                placeholder = "Add fields and sections and click \"Update preview\"..."
            }
        }
    }
}