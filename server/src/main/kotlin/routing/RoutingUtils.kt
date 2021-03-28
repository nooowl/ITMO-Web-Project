package routing

import io.ktor.application.*
import io.ktor.request.*
import makefile.MakefileData
import makefile.MakefileValidationException
import makefile.validateMakefile

suspend fun ApplicationCall.receiveMakefile(): MakefileData.Makefile {
    val makefile: MakefileData.Makefile
    try {
        makefile = this.receive()
    } catch (e: Exception) {
        throw MakefileValidationException("Can't parse Makefile data", e)
    }
    makefile.validateMakefile()
    return makefile
}