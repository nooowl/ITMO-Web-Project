package specfile

import makefile.toMakefileText

sealed class SpecfileData {
    data class Specfile(
        val defines: List<SpecfileDefine>,
        val fields: List<SpecfileField>,
        val sections: List<SpecfileSection>
    ) : SpecfileData()

    data class SpecfileDefine(val name: String, val value: String) : SpecfileData()
    data class SpecfileField(val name: String, val value: String) : SpecfileData()
    data class SpecfileSection(val name: String, val body: String) : SpecfileData()
}

fun SpecfileData.toSpecfileText(definesIndent: Int = 0, fieldsIndent: Int = 0): String {
    return when (this) {
        is SpecfileData.Specfile -> {
            val definesString = defines.joinToString("\n") {
                it.toSpecfileText(definesIndent = defines.map { v -> v.name.length }.maxOrNull()!!)
            }
            val spaces = (if (defines.isNotEmpty()) "\n\n" else "")
            val fieldsString = fields.joinToString("\n") {
                it.toSpecfileText(fieldsIndent = fields.map { v -> v.name.length }.maxOrNull()!!)
            } + "\n\n"
            val sectionsString = sections.joinToString("\n\n\n") { it.toSpecfileText() }
            return definesString + spaces + fieldsString + sectionsString
        }
        is SpecfileData.SpecfileDefine -> "%define $name${" ".repeat(definesIndent - name.length)} $value"
        is SpecfileData.SpecfileField -> "$name:${" ".repeat(fieldsIndent - name.length)} $value"
        is SpecfileData.SpecfileSection -> "${name}\n" +
                body.split("\n").joinToString("\n")
    }
}
