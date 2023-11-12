import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":common"))
    implementation(project(":catalog"))
    implementation("org.springframework:spring-tx")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

}

tasks.named<BootJar>("bootJar") {
    enabled = true
    mainClass.set("cokeaswater.cstore.api.ApiApplicationKt")
}
