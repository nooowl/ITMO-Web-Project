package collectors

import html.*
import org.w3c.dom.*
import kotlin.js.Json
import kotlin.js.json

fun collectDefinesToJson(): List<Json> {
    val definesJson = mutableListOf<Json>()
    val defines = definesDiv.getElementsByClassName("define").asList()
    for (define in defines) {
        definesJson.add(
            json(
                "name" to define.getChildById<HTMLInputElement>("name").value,
                "value" to define.getChildById<HTMLInputElement>("value").value
            )
        )
    }
    return definesJson
}

fun collectFieldsToJson(): List<Json> {
    val fieldsJson = mutableListOf<Json>()
    val fields = fieldsDiv.getElementsByClassName("field").asList() +
            extraFieldsDiv.getElementsByClassName("field").asList()
    for (field in fields) {
        fieldsJson.add(
            json(
                "name" to field.getChildById<HTMLInputElement>("name").value,
                "value" to field.getChildById<HTMLInputElement>("value").value
            )
        )
    }
    return fieldsJson
}

fun collectSectionsToJson(): List<Json> {
    val sectionsJson = mutableListOf<Json>()
    val sections = sectionsDiv.getElementsByClassName("section").asList() +
            extraSectionsDiv.getElementsByClassName("section").asList()
    for (section in sections) {
        sectionsJson.add(
            json(
                "name" to "%${section.getChildById<HTMLInputElement>("name").value}",
                "body" to section.getChildById<HTMLTextAreaElement>("body").value
            )
        )
    }
    return sectionsJson
}


fun collectSpecfileInfoToJson(): Json = json(
    "defines" to collectDefinesToJson(),
    "fields" to collectFieldsToJson(),
    "sections" to collectSectionsToJson()
)