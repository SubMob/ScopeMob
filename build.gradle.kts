/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ClassPaths.kotlinGradlePlugin)
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
