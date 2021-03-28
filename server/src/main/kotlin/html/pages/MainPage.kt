package html.pages

import html.baseHead
import kotlinx.html.*

fun HTML.mainPage() {
    baseHead("Main")
    body {
        ul {
            li {
                a("/createMakefile") {
                    +"Create Makefile"
                }
            }
            li {
                a("/createDockerfile") {
                    +"Create Dockerfile"
                }
            }
        }
    }
}