/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        with(Classpaths) {
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
