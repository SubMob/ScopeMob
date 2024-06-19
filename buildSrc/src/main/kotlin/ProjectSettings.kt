/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.gradle.api.Project

object ProjectSettings {
    private const val MAYOR_VERSION = 2
    private const val MINOR_VERSION = 1

    // git rev-list --first-parent --count master +1
    private const val VERSION_DIF = 95

    fun getVersionName(
        project: Project
    ) = "$MAYOR_VERSION.$MINOR_VERSION.${gitCommitCount(project).toInt() - VERSION_DIF}"

    @Suppress("UnstableApiUsage")
    private fun gitCommitCount(project: Project): String = project.providers.exec {
        commandLine("git rev-list --first-parent --count HEAD".split(" "))
    }.standardOutput.asText.get().trim()
}
