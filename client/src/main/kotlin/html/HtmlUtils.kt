package html

import org.w3c.dom.HTMLElement

@Suppress("UNCHECKED_CAST")
fun <T : HTMLElement> HTMLElement.getChildById(id: String): T = this.querySelector("#$id") as T