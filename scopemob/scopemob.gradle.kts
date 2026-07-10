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
    // Sign releases only — Central Portal snapshots aren't signed, and the signing key is provided
    // to release CI via ORG_GRADLE_PROJECT_* env.
    if (!version.toString().endsWith("SNAPSHOT")) {
        signAllPublications()
    }
}
