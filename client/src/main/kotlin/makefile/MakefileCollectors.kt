package makefile

import html.getChildById
import html.targetsDiv
import html.variablesDiv
import org.w3c.dom.*
import kotlin.js.Json
import kotlin.js.json

fun collectTargetsToJson(): List<Json> {
    val targetsJson = mutableListOf<Json>()
    val targets = targetsDiv.getElementsByClassName("target")
    for (i in 0 until targets.length) {
        val target = targets[i] as HTMLDivElement
        targetsJson.add(
            json(
                "name" to target.getChildById<HTMLInputElement>("name").value,
                "dependencies" to target.getChildById<HTMLInputElement>("dependencies").value,
                "body" to target.getChildById<HTMLTextAreaElement>("body").value
            )
        )
    }
    return targetsJson
}

fun collectVariablesToJson(): List<Json> {
    val variablesJson = mutableListOf<Json>()
    val variables = variablesDiv.getElementsByClassName("variable")
    for (i in 0 until variables.length) {
        val variable = variables[i] as HTMLDivElement
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