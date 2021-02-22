/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
enableFeaturePreview("GRADLE_METADATA")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
}

rootProject.name = "scopemob"
include(":scopemob")
