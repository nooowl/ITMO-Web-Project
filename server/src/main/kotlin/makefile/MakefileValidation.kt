package makefile

import java.util.stream.Collectors


class MakefileValidationException(message: String, cause: Throwable? = null) : Exception(message, cause)

fun MakefileData.Makefile.validateMakefile() {
    if (variables.any { it.name.isBlank() }) {
        throw MakefileValidationException("Can't create variable with empty name")
    }

    if (variables.any { it.value.isBlank() }) {
        throw MakefileValidationException("Can't create variable with empty value")
    }

    val groupedVariables = variables.stream().collect(Collectors.groupingBy({ it.name }, Collectors.counting()))
    val duplicatedVariables = groupedVariables.filter { it.value > 1 }.map { it.key }
    if (duplicatedVariables.isNotEmpty()) {
        throw MakefileValidationException("Use different name for variables: [${duplicatedVariables.joinToString()}]")
    }

    if (targets.isEmpty()) {
        throw MakefileValidationException("Create at least one target")
    }

    if (targets.any { it.name.isBlank() }) {
        throw MakefileValidationException("Can't create target with empty name")
    }

    val groupedTargets = targets.stream().collect(Collectors.groupingBy({ it.name }, Collectors.counting()))
    val duplicatedTargets = groupedTargets.filter { it.value > 1 }.map { it.key }
    if (duplicatedTargets.isNotEmpty()) {
        throw MakefileValidationException("Use different name for targets: [${duplicatedTargets.joinToString()}]")
    }

    val uselessTargets = targets.filter { it.body.isEmpty() && it.dependencies.isEmpty() }
    if (uselessTargets.isNotEmpty()) {
        throw MakefileValidationException("Add body or dependencies for targets: [${uselessTargets.joinToString { it.name }}]")
    }
}