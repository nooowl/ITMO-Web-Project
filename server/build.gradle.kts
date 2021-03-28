plugins {
    id("java")
    kotlin("jvm")
    id("application")
    id("distribution")
}

val ktorVersion = project.property("ktor.version") as String
val logbackVersion = project.property("logback.version") as String

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks.withType<Copy>().named("processResources") {
    from(project(":client").tasks.named("browserDistribution"))
}

