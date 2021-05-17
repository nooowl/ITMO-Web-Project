package specfile

class SpecfileValidationException(message: String, cause: Throwable? = null) : Exception(message, cause)

fun SpecfileData.Specfile.validateSpecfile() {
    if (defines.any { it.name.isBlank() }) {
        throw SpecfileValidationException("Can't create %define with empty name")
    }

    if (defines.any { it.value.isBlank() }) {
        throw SpecfileValidationException("Can't create %define with empty value")
    }

    if (fields.any { it.name.isBlank() }) {
        throw SpecfileValidationException("Can't create field with empty name")
    }

    if (fields.any { it.value.isBlank() }) {
        throw SpecfileValidationException("Can't create field with empty value")
    }

    if (sections.any { it.name.isBlank() }) {
        throw SpecfileValidationException("Can't create section with empty name")
    }
}