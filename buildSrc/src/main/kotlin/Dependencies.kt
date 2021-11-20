/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

object Dependencies {
    object Common {
        const val TEST = "test-common"
        const val TEST_ANNOTATIONS = "test-annotations-common"
    }

    object JVM {
        const val TEST_J_UNIT = "test-junit"
    }

    object JS {
        const val TEST = "test-js"
    }

    object ClassPaths {
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    }

    object Plugins {
        const val MULTIPLATFORM = "multiplatform"
    }
}
