/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    with(ScopeMob.Conf) {
        compileSdkVersion(projectCompileSdkVersion)

        defaultConfig {
            minSdkVersion(projectMinSdkVersion)
            targetSdkVersion(projectTargetSdkVersion)

            versionCode = getVersionCode(project)
            versionName = getVersionName(project)
        }
    }
}

dependencies {
    implementation(ScopeMob.Dependencies.kotlin)
    testImplementation(ScopeMob.TestDependencies.jUnit)
}
