package html

import kotlinx.html.*

fun HTML.baseHead(name: String, css: String? = null, js: String? = null) {
    lang = "en"
    head {
        title {
            +name
        }
        if (css != null) {
            link {
                href = "/css/$css.css"
                rel = "stylesheet"
            }
        }
        if (js != null) {
            script {
                src = "/$js.js"
            }
        }
    }
}

fun HTML.baseBody(currentLink: String, buttons: DIV.() -> Unit = {}, content: DIV.() -> Unit) {
    body {
        div("main-body") {
            div("sticky") {
                titleAndNavigation(currentLink)
                div("buttons") {
                    buttons()
                }
            }
            div("main") {
                content()
            }

            footer {
                div("footer") {
                    p {
                        +"© Russkih Polina"
                    }
                }
            }
        }
    }
}

fun FlowContent.titleAndNavigation(currentLink: String) {
    div("title") {
        h1 { +"RPM Build Helper" }
    }
    nav {
        ul("navigate") {
            navigationElement("Main", "/", currentLink)
            navigationElement("Makefile Creator", "/createMakefile", currentLink)
            navigationElement("Specfile Creator", "/createSpecfile", currentLink)
            navigationElement("Information", "/information", currentLink)
        }
    }
}

fun UL.navigationElement(text: String, link: String, currentLink: String) {
    li {
        a(if (currentLink == link) "#" else link) { +text; }
    }
}

fun FlowOrInteractiveOrPhrasingContent.createButton(id: String, text: String) =
    a("#", classes = "button") {
        this.id = id
        +text
    }