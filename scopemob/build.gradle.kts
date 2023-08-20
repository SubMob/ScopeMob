/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    id(libs.plugins.multiplatform.get().pluginId)
    `maven-publish`
    signing
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @Suppress("UNUSED_VARIABLE")
    sourceSets {
        val commonTest by getting {
            dependencies {
                libs.common.apply {
                    implementation(test)
                    implementation(testAnnotations)
                }
            }
        }
    }
}
