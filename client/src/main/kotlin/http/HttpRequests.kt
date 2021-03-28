package http

import kotlinx.browser.window
import org.w3c.dom.events.Event
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Json

fun XMLHttpRequest.sendAndProcessResult(
    method: String,
    url: String,
    data: Json? = null,
    onSuccess: ((Event) -> dynamic)? = null
) {
    this.open(method, url, true)
    this.onload = {
        when (this.status.toInt()) {
            in 200..399 -> onSuccess?.invoke(it)
            400 -> window.alert(this.responseText)
            else -> Unit
        }
    }

    if (data != null) {
        this.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
        this.send(JSON.stringify(data))
    } else {
        this.send()
    }
}