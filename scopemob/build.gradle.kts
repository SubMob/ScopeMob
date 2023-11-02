/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    `maven-publish`
    signing
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.commonTest.dependencies {
        libs.common.apply {
            implementation(test)
            implementation(testAnnotations)
        }
    }
}
