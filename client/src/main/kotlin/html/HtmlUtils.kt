package html

import org.w3c.dom.Element

@Suppress("UNCHECKED_CAST")
fun <T : Element> Element.getChildById(id: String): T = this.querySelector("#$id") as T