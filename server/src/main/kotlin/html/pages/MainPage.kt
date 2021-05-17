package html.pages

import html.baseBody
import html.baseHead
import html.titleAndNavigation
import kotlinx.html.*

fun HTML.mainPage() {
    baseHead("Main", "style")
    baseBody("/") {
        div("main-description") {
            h2("about site") {
                + "About site:"
            }
            + "This site is designed for easy generation of "
            b { +"make" }
            + " and "
            b { +"spec" }
            +" files that need to build rpm packages."
            h2 {
                + "Opportunities:"
            }
            h4 {
                + " Makefile:"
            }
            + "During the Makefile creation process, you can create, add, and delete variables and targets. "
            +"Next, you can view a preview of the file, download the makefile, or clear all fields. "
            + "When downloading, you need to specify the file name, otherwise the file will be called "
            + "\"Makefile file\"."
            h4 {
                + " .spec file:"
            }
            + "In the .spec file, you can add defines, additional fields, and sections. "
            + "The necessary fields and sections have already been added to the page, "
            + "for example, the \"Name\" and \"Version\" fields. "
            + "The rest of the fields need to be added, but they have hints of the main options. "
            + "Similarly with sections. You can also view a preview or download the resulting file."
        }
    }
}