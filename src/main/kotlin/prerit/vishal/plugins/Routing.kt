package prerit.vishal.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {

//      Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }

        singlePageApplication {
            useResources = true
            filesPath = "static"
            defaultPage = "index.html"

        }

    }
}
