import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    id("io.spring.dependency-management") version "1.1.3" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.20" apply false
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.20" apply false
    id("org.springframework.boot") version "3.1.5" apply false
    kotlin("kapt") version "1.9.20" apply false
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "cokeaswater.cstore"
    version = "0.0.1-SNAPSHOT"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += listOf("-Xjsr305=strict", "-Xjvm-default=all")
            jvmTarget = "17"
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-kapt")

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2022.0.4")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("io.github.microutils:kotlin-logging:3.0.5")
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.2")
        implementation("com.github.f4b6a3:ulid-creator:5.2.2")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    }


    tasks.named<BootJar>("bootJar") {
        enabled = false
    }
}

tasks.named<Jar>("jar") {
    enabled = false
}

