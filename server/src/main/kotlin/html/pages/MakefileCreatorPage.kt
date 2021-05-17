package html.pages

import html.baseBody
import html.baseHead
import html.createButton
import html.titleAndNavigation
import kotlinx.html.*

fun HTML.makefileCreatorPage() {
    baseHead("Create Makefile", "style", "client")
    baseBody("/createMakefile", {
        createButton("add-target-button", "Add target")
        createButton("add-variable-button", "Add variable")
        createButton("preview-button", "Update preview")
        createButton("download-button", "Download Makefile")
        createButton("clear-all", "Clear All")
    }) {
        div("creator main-flex") {
            h3 { label { +"Variables:" } }
            div("variables") {
                id = "variables"
            }
            div("targets") {
                id = "targets"
                h3 { label { +"Targets:" } }
                dataList {
                    id = "dependencies-list"
                }
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
                placeholder = "Add targets and variables and click \"Update preview\"..."
            }
        }
    }
}