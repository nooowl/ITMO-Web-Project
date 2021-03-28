package html

import kotlinx.html.*

fun HTML.baseHead(name: String, css: String? = null, js: String? = null) =
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

