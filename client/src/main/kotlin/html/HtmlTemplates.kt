package html

import kotlinx.html.*
import org.w3c.dom.HTMLElement

// region Makefile

fun TagConsumer<HTMLElement>.createMakefileTarget() =
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
                    list = "dependencies-list"
                    autoComplete = false
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

fun TagConsumer<HTMLElement>.createMakefileVariable() =
    div("variable") {
        id = "variable"
        div("name variable-flex") {
            label {
                htmlFor = "name"
                +"Name:"
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

// endregion

// region Specfile

fun TagConsumer<HTMLElement>.createSpecfileDefine() =
    div("define") {
        id = "define"
        div("name define-flex") {
            label {
                htmlFor = "name"
                +"%define "
            }
            input {
                id = "name"
                type = InputType.text
            }
        }
        div("value define-flex") {
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
            id = "delete-define"
        }
    }

fun TagConsumer<HTMLElement>.createSpecfileField(canEdit: Boolean = true) =
    div("field") {
        id = "field"
        div("name field-flex") {
            input {
                id = "name"
                type = InputType.text
                list = "fields-list"
                autoComplete = false
                if (!canEdit) disabled = true
            }
        }
        div("value field-flex") {
            label {
                htmlFor = "value"
                +"Value:"
            }
            input {
                id = "value"
                type = InputType.text
            }
        }
        if (canEdit) {
            img(src = "images/cross.jpg", classes = "delete") {
                id = "delete-field"
            }
        }
    }

fun TagConsumer<HTMLElement>.createSpecfileSection(canEdit: Boolean = true) =
    div("section") {
        if (canEdit) {
            img(src = "images/cross.jpg", classes = "delete") {
                id = "delete-section"
            }
        }
        div("title") {
            div("name title-flex") {
                label {
                    htmlFor = "name"
                    +"Section name: %"
                }
                input {
                    id = "name"
                    type = InputType.text
                    list = "sections-list"
                    autoComplete = false
                    if (!canEdit) disabled = true
                }
            }
        }
        div("body") {
            label {
                htmlFor = "body"
                +"Section body:"
            }
            textArea {
                id = "body"
            }
        }
    }

// endregion