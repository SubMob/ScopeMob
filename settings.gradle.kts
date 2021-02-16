/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}

rootProject.name = "scopemob"
include(":submob")
