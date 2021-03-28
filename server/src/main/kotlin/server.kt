import html.pages.dockerfileCreatorPage
import html.pages.mainPage
import html.pages.makefileCreatorPage
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import makefile.MakefileData
import makefile.MakefileValidationException
import makefile.toMakefileText
import routing.receiveMakefile
import java.io.File
import java.text.DateFormat
import java.util.*

val random = Random()

fun Application.installGson() {
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
}

suspend inline fun PipelineContext<Unit, ApplicationCall>.respondMakefileOrError(
    crossinline respondAction: suspend PipelineContext<Unit, ApplicationCall>.(MakefileData.Makefile) -> Unit
) {
    try {
        val makefile = call.receiveMakefile()
        this.respondAction(makefile)
    } catch (mve: MakefileValidationException) {
        call.respondText(status = HttpStatusCode.BadRequest) { mve.message ?: "Bad request" }
    }
}

@Suppress("Unused")
fun Application.main() {
    installGson()

    routing {
        static("/") {
            resources("/")
        }

        get("/") {
            call.respondHtml {
                mainPage()
            }
        }

        get("/createDockerfile") {
            call.respondHtml {
                dockerfileCreatorPage()
            }
        }

        get("/createMakefile") {
            call.respondHtml {
                makefileCreatorPage()
            }
        }

        post("/previewMakefile") {
            respondMakefileOrError { call.respondText { it.toMakefileText() } }
        }

        post("/downloadMakefile") {
            respondMakefileOrError {
                val file = File("Makefile-${random.nextLong()}")
                    .apply { writeText(it.toMakefileText()) }
                try {
                    call.respondFile(file)
                } finally {
                    file.delete()
                }
            }
        }
    }
}


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)