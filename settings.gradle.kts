/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

plugins {
    id("com.gradle.enterprise") version ("3.16.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()

        obfuscation {
            username { null }
            hostname { null }
            ipAddresses { null }
        }
    }
}

rootProject.name = "ScopeMob"
include(":scopemob")
