/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("SpellCheckingInspection")
/*
Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
*/

object ScopeMob {
    object Versions {
        const val kotlinVersion = "1.3.72"
        const val androidPluginVersion = "4.0.1"
        const val jUnitVersion = "4.13"
    }

    object Dependencies {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    }

    object Tests {
        const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    }

    object Classpaths {
        const val androidBuildTools = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object Configurations {
        private const val mayorVersion = 2
        private const val minorVersion = 0

        const val compileSdkVersion = 29
        const val minSdkVersion = 21
        const val targetSdkVersion = 29

        fun getVersionCode(project: Project) = gitCommitCount(project).let {
            if (it.isEmpty()) 1 else it.toInt()
        }

        fun getVersionName(project: Project) = "$mayorVersion.$minorVersion.${gitCommitCount(project)}"

        private fun gitCommitCount(project: Project) =
            "git rev-list --first-parent --count origin/master"
                .executeCommand(project.rootDir)?.trim()
                ?: ""

        @Suppress("SpreadOperator", "MagicNumber")
        private fun String.executeCommand(workingDir: File): String? = try {
            val parts = this.split("\\s".toRegex())
            ProcessBuilder(*parts.toTypedArray())
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start().run {
                    waitFor(10, TimeUnit.SECONDS)
                    inputStream.bufferedReader().readText()
                }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}