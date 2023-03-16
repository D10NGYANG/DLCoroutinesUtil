plugins {
    kotlin("multiplatform") version "1.8.10"
    id("maven-publish")
    id("dev.petuska.npm.publish") version "3.2.1"
}

group = "com.github.D10NGYANG"
version = "0.4.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io" )
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js(IR) {
        moduleName = "dl-coroutines-util"
        browser()
        binaries.library()
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                // 协程
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

val bds100MavenUsername: String by project
val bds100MavenPassword: String by project
publishing {
    repositories {
        maven {
            url = uri("/Users/d10ng/project/kotlin/maven-repo/repository")
        }
        maven {
            credentials {
                username = bds100MavenUsername
                password = bds100MavenPassword
            }
            setUrl("https://nexus.bds100.com/repository/maven-releases/")
        }
    }
}

npmPublish {
    registries {
        register("npm-hosted") {
            uri.set("https://nexus.bds100.com/repository/npm-hosted")
        }
    }
    packages {
        named("js") {
            scope.set("hailiao")
            packageName.set("dl-coroutines-util")
        }
    }
}