/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ScopeMob.Classpaths.androidBuildTools)
        classpath(ScopeMob.Classpaths.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
