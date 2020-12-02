/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("SpellCheckingInspection")

import org.gradle.api.Project
import java.io.File
import java.util.concurrent.TimeUnit

object Versions {
    const val kotlin = "1.4.20"
    const val androidPlugin = "7.0.0-alpha01"
}

object Dependencies {
    object Common {
        const val test = "test-common"
        const val testAnnotations = "test-annotations-common"
    }

    object JVM {
        const val testJUnit = "test-junit"
    }

    object JS {
        const val test = "test-js"
    }
}

object Classpaths {
    const val androidBuildTools =
        "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Plugins {
    const val androidLibrary = "com.android.library"
    const val multiplatform = "multiplatform"
}

object ProjectSettings {
    private const val mayorVersion = 2
    private const val minorVersion = 0

    const val projectCompileSdkVersion = 29
    const val projectMinSdkVersion = 21
    const val projectTargetSdkVersion = 29

    fun getVersionCode(project: Project) = gitCommitCount(project).let {
        if (it.isEmpty()) 1 else it.toInt()
    }

    fun getVersionName(project: Project) =
        "$mayorVersion.$minorVersion.${gitCommitCount(project)}"

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
    } catch (e: java.io.IOException) {
        e.printStackTrace()
        null
    }
}
