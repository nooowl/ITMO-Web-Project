package html.pages

import html.baseHead
import kotlinx.html.*

fun HTML.makefileCreatorPage() {
    lang = "en"
    baseHead("Create Makefile", "style", "client")
    body {
        div("main-body") {
            div("sticky") {
                div("title") {
                    h1 { +"Makefile Creator" }
                }
                nav {
                    ul("navigate") {
                        li {
                            a("/") { +"Main" }
                        }
                        li {
                            a("#") { +"Makefile Creator" }
                        }
                    }
                }
                div("buttons") {
                    createButton("add-target-button", "Add target")
                    createButton("add-variable-button", "Add variable")
                    createButton("preview-button", "Update preview")
                    createButton("download-button", "Download Makefile")
                }
            }
            div("main") {
                div("creator main-flex") {
                    h3 { label { +"Variables:" } }
                    div("variables") {
                        id = "variables"
                    }
                    div("targets") {
                        id = "targets"
                        h3 { label { +"Targets:" } }
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
                        placeholder = "Add targets and click \"Update preview\"..."
                    }
                }
            }
        }
    }
}

fun FlowOrInteractiveOrPhrasingContent.createButton(id: String, text: String) =
    a("#", classes = "button") {
        this.id = id
        +text
    }