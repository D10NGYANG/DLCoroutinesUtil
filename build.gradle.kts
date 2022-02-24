plugins {
    kotlin("multiplatform") version "1.6.10"
    id("maven-publish")
}

group = "com.d10ng"

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
    /*js(LEGACY) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }*/
    val publicationsFromMainHost =
        listOf(jvm()/*, js()*/).map { it.name } + "kotlinMultiplatform"
    publishing {
        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                // 协程
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}