/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

object Dependencies {
    object Common {
        const val TEST = "test"
        const val TEST_ANNOTATIONS = "test-annotations-common"
    }

    object ClassPaths {
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
        const val KOVER = "org.jetbrains.kotlinx:kover:${Versions.KOVER}"
    }

    object Plugins {
        const val MULTIPLATFORM = "multiplatform"
        const val KOVER = "org.jetbrains.kotlinx.kover"
    }
}
