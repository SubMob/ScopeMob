/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    libs.plugins.apply {
        alias(kotlinMultiplatform).apply(false)
        alias(mavenPublish).apply(false)
        alias(kover)
    }
}

allprojects {
    project.dependencyLocking.lockAllConfigurations()

    apply(plugin = rootProject.libs.plugins.kover.get().pluginId).also {
        rootProject.dependencies.add("kover", project(path))
    }

    // Group + POM metadata + Central Portal config come from gradle.properties (read by
    // com.vanniktech.maven.publish). Only the version is dynamic (git commit count).
    version = ProjectSettings.getVersionName(project)

    repositories {
        google()
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            allWarningsAsErrors = true
        }
    }
}

// Resolved version, read by CI to tag the release. The VERSION_NAME= prefix keeps it parseable
// even when Gradle writes build-scan output to the same stream.
tasks.register("printVersion") {
    val projectVersion = version.toString()
    doLast { println("VERSION_NAME=$projectVersion") }
}
