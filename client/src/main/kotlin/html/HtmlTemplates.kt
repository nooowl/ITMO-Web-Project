package html

import kotlinx.html.*
import org.w3c.dom.HTMLElement

fun TagConsumer<HTMLElement>.createTarget() =
    div("target") {
        id = "target"
        img(src = "images/cross.jpg", classes = "delete") {
            id = "delete-target"
        }
        div("title") {
            div("name title-flex") {
                label {
                    htmlFor = "name"
                    +"Target name:"
                }
                input {
                    id = "name"
                    type = InputType.text
                }
            }
            div("colon title-flex") { +":" }
            div("dependencies title-flex") {
                label {
                    htmlFor = "dependencies"
                    +"Dependencies:"
                }
                input {
                    id = "dependencies"
                    type = InputType.text
                }
            }
        }
        div("body") {
            label {
                htmlFor = "body"
                +"Target body:"
            }
            textArea {
                id = "body"
            }
        }
    }

fun TagConsumer<HTMLElement>.createVariable() =
    div("variable") {
        id = "variable"
        div("name variable-flex") {
            label {
                htmlFor = "name"
                +"Variable name:"
            }
            input {
                id = "name"
                type = InputType.text
            }
        }
        div("equal variable-flex") {
            label {
                select {
                    id = "type"
                    option { +"=" }
                    option { +":=" }
                    option { +"?=" }
                    option { +"+=" }
                }
            }
        }
        div("value variable-flex") {
            label {
                htmlFor = "value"
                +"Value:"
            }
            input {
                id = "value"
                type = InputType.text
            }
        }
        img(src = "images/cross.jpg", classes = "delete") {
            id = "delete-variable"
        }
    }