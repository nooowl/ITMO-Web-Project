package makefile

sealed class MakefileData {
    data class MakefileTarget(val name: String, val body: String, val dependencies: String) : MakefileData()
    data class MakefileVariable(val name: String, val type: String, val value: String) : MakefileData()
    data class Makefile(val variables: List<MakefileVariable>, val targets: List<MakefileTarget>) : MakefileData()
}

fun MakefileData.toMakefileText(varIndent: Int = 0): String {
    return when (this) {
        is MakefileData.Makefile ->
            variables.joinToString("\n") {
                it.toMakefileText(variables.map { v -> v.name.length }.maxOrNull()!!)
            } + (if (variables.isNotEmpty()) "\n\n\n" else "") +
                    targets.joinToString("\n\n\n") { it.toMakefileText() }

        is MakefileData.MakefileTarget -> "${name}: " +
                dependencies.replace("\\s+".toRegex(), " ") + "\n" +
                body.split("\n").joinToString("\n") { "  $it" }

        is MakefileData.MakefileVariable -> "$name${" ".repeat(varIndent - name.length)} $type $value"
    }
}