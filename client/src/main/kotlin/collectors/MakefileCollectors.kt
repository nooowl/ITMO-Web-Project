package collectors

import html.getChildById
import html.targetsDiv
import html.variablesDiv
import org.w3c.dom.*
import kotlin.js.Json
import kotlin.js.json

fun collectTargetsToJson(): List<Json> {
    val targetsJson = mutableListOf<Json>()
    val targets = targetsDiv.getElementsByClassName("target").asList()
    for (target in targets) {
        targetsJson.add(
            json(
                "name" to target.getChildById<HTMLInputElement>("name").value,
                "dependencies" to target.getChildById<HTMLSelectElement>("dependencies").value,
                "body" to target.getChildById<HTMLTextAreaElement>("body").value
            )
        )
    }
    return targetsJson
}

fun collectVariablesToJson(): List<Json> {
    val variablesJson = mutableListOf<Json>()
    val variables = variablesDiv.getElementsByClassName("variable").asList()
    for (variable in variables) {
        variablesJson.add(
            json(
                "name" to variable.getChildById<HTMLInputElement>("name").value,
                "type" to variable.getChildById<HTMLSelectElement>("type").value,
                "value" to variable.getChildById<HTMLInputElement>("value").value
            )
        )
    }
    return variablesJson
}

fun collectMakefileInfoToJson(): Json = json(
    "variables" to collectVariablesToJson(),
    "targets" to collectTargetsToJson()
)