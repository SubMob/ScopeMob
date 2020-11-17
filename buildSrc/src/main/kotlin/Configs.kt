/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
@file:Suppress("SpellCheckingInspection")

import org.gradle.api.Project
import java.io.File
import java.util.concurrent.TimeUnit

object Versions {
    const val kotlinVersion = "1.4.0"
    const val androidPluginVersion = "4.0.1"
    const val jUnitVersion = "4.13"
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

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
}

object Classpaths {
    const val androidBuildTools =
        "com.android.tools.build:gradle:${Versions.androidPluginVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object Plugins {
    const val androidLibrary = "com.android.library"
    const val multiplatform = "multiplatform"
}

object ProjectSettings {
    private const val projectMayorVersion = 2
    private const val projectMinorVersion = 0

    const val projectCompileSdkVersion = 29
    const val projectMinSdkVersion = 21
    const val projectTargetSdkVersion = 29

    fun getVersionCode(project: Project) = gitCommitCount(project).let {
        if (it.isEmpty()) 1 else it.toInt()
    }

    fun getVersionName(project: Project) =
        "$projectMayorVersion.$projectMinorVersion.${gitCommitCount(project)}"

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
