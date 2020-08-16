/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        with(ScopeMob.Classpaths) {
            classpath(androidBuildTools)
            classpath(kotlinGradlePlugin)
        }
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
