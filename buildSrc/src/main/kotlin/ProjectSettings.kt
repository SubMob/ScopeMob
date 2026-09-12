/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.gradle.api.Project

object ProjectSettings {
    private const val MAYOR_VERSION = 3
    private const val MINOR_VERSION = 0

    // git rev-list --first-parent --count develop (recalibrated when releases moved off master)
    private const val VERSION_DIF = 422

    fun getVersionName(project: Project): String = if (isReleaseBranch(project)) {
        // Permanent, pinnable release: x.y.<commit-count>. Every merge to develop publishes one.
        "$MAYOR_VERSION.$MINOR_VERSION.${gitCommitCount(project).toInt() - VERSION_DIF}"
    } else {
        // Feature branches and local builds — never published, so the moving snapshot is fine.
        "$MAYOR_VERSION.$MINOR_VERSION-SNAPSHOT"
    }

    private fun isReleaseBranch(project: Project): Boolean = currentBranch(project) == "develop"

    private fun currentBranch(project: Project): String {
        // In GitHub Actions the checked-out branch is exposed as GITHUB_REF_NAME; fall back to git locally.
        val ciBranch = project.providers.environmentVariable("GITHUB_REF_NAME").orNull
        if (!ciBranch.isNullOrBlank()) return ciBranch
        return project.providers.exec {
            commandLine("git rev-parse --abbrev-ref HEAD".split(" "))
        }.standardOutput.asText.get().trim()
    }

    private fun gitCommitCount(project: Project): String = project.providers.exec {
        commandLine("git rev-list --first-parent --count HEAD".split(" "))
    }.standardOutput.asText.get().trim()
}
