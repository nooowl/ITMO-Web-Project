package routing

import io.ktor.application.*
import io.ktor.request.*
import makefile.MakefileData
import makefile.MakefileValidationException
import makefile.validateMakefile
import specfile.SpecfileData
import specfile.validateSpecfile

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

suspend fun ApplicationCall.receiveSpecfile(): SpecfileData.Specfile {
    val makefile: SpecfileData.Specfile
    try {
        makefile = this.receive()
    } catch (e: Exception) {
        throw MakefileValidationException("Can't parse Specfile data", e)
    }
    makefile.validateSpecfile()
    return makefile
}