plugins {
    kotlin("js")
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.2")
    implementation("com.google.code.gson:gson:2.8.6")
}

kotlin {
    js {
        browser {}
        binaries.executable()
    }
}