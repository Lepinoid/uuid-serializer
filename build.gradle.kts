plugins {
    kotlin("multiplatform") version "1.6.20"
    kotlin("plugin.serialization") version "1.5.20"
    `maven-publish`
}

group = "net.lepinoid"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    sourceSets {
        val commonMain by getting  {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
                implementation("com.benasher44:uuid:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}

publishing {
    publications.all {
        this as MavenPublication
        pom {
            name.set(project.name)
            url.set("https://github.com/Lepinoid/UuidSerializer")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://github.com/Lepinoid/UuidSerializer/blob/main/LICENSE")
                }
            }
        }
    }
    repositories.maven {
        url = uri("${System.getProperty("user.home")}/lepinoid/maven-repo")
    }
}
