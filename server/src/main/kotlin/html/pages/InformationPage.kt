package html.pages

import html.baseBody
import html.baseHead
import html.titleAndNavigation
import kotlinx.html.*

fun HTML.informationPage() {
    baseHead("Information", "style")
    baseBody("/information") {
        div("main-description") {
            h2 { + "What is rpm?" }
            + "The "
            b { + "RPM Package Manager" }
            + "(RPM) is a package management system that runs on Red Hat Enterprise Linux, "
            + "CentOS, and Fedora. RPM makes it easier for you to distribute, "
            + "manage, and update software that you create for Red Hat Enterprise Linux, CentOS, and Fedora."
            h4 { + "More info about rpm:"}
            a("https://en.wikipedia.org/wiki/RPM_Package_Manager#SPEC_file") {
                 + "https://en.wikipedia.org/wiki/RPM_Package_Manager#SPEC_file"
            }

            p {
                a("http://wiki.rosalab.ru/ru/index.php/%D0%9E%D1%81%D0%BD%D0%BE%D0%B2%D1%8B_RPM") {
                    +"http://wiki.rosalab.ru/ru/index.php/Основы_RPM"
                }
            }
            h2 { + ".spec file:" }
            + "The \"Recipe\" for creating an RPM package is a spec file. "
            + "Spec files end in the \".spec\" suffix and contain the package name, version, "
            + "RPM revision number, steps to build, install, and clean a package, and a changelog. "
            + "Multiple packages can be built from a single RPM spec file, if desired. "
            + "RPM packages are created from RPM spec files using the rpmbuild tool."
            h4 { + "More info about .spec:"}
            a("https://wiki.mageia.org.ru/index.php?title=%D0%A1%D1%82%D1%80%D1%83%D0%BA%D1%82%D1%83%D1%80%D0%B0_RPM_SPEC-%D1%84%D0%B0%D0%B9%D0%BB%D0%B0") {
                + "https://wiki.mageia.org.ru/index.php?title=Структура_RPM_SPEC-файла"
            }
            h2 { + "Makefile:" }
            + "Instead of building the source code manually, you can automate the building. "
            + "This is a common practice used by large-scale software. "
            + "Automating building is done by creating a Makefile and then running the GNU make utility."
            h4 { + "More info about .spec:"}
            a( "https://ru.wikipedia.org/wiki/Makefile" ) {
                + "https://ru.wikipedia.org/wiki/Makefile"
            }
        }
    }
}