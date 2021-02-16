/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        with(ClassPaths) {
            classpath(androidBuildTools)
            classpath(kotlinGradlePlugin)
        }
    }
}

version = "2.0.0"

allprojects {
    repositories {
        jcenter()
        google()
    }
}
