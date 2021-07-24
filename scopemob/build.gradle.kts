/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    kotlin(Plugins.MULTIPLATFORM)
    `maven-publish`
    signing
}

kotlin {

    jvm()

    // todo Revert to just ios() when gradle plugin can properly resolve it
    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    js {
        browser {
            binaries.executable()
            testTask {
                enabled = false
            }
        }
    }

    @Suppress("UNUSED_VARIABLE")
    sourceSets {

        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Common.TEST))
                implementation(kotlin(Dependencies.Common.TEST_ANNOTATIONS))
            }
        }

        val iosMain by getting
        val iosTest by getting

        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.JVM.TEST_J_UNIT))
            }
        }

        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.JS.TEST))
            }
        }
    }
}
