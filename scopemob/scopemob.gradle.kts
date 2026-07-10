/*
 * Copyright (c) 2021 Mustafa Ozhan. All rights reserved.
 */

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.mavenPublish)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets.commonTest.dependencies {
        libs.common.apply {
            implementation(test)
        }
    }
}

mavenPublishing {
    // Coordinates (GROUP + POM_ARTIFACT_ID), POM, host and auto-release come from gradle.properties.
    // Central Portal requires signed artifacts; keys are provided in CI via ORG_GRADLE_PROJECT_* env.
    signAllPublications()
}
