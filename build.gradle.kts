/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.io.IOException
import java.util.Properties

plugins {
    `maven-publish`
    @Suppress("DSL_SCOPE_VIOLATION")
    with(libs.plugins) {
        alias(dependencyUpdates)
        alias(kover)
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        with(libs.classpaths) {
            classpath(kotlinGradlePlugin)
            classpath(kover)
        }
    }
}

allprojects {

    apply(plugin = "kover")

    with(Library) {

        group = GROUP
        version = ProjectSettings.getVersionName(project)

        repositories {
            google()
            mavenCentral()
        }

        val emptyJavadocJar by tasks.registering(Jar::class) {
            archiveClassifier.set("javadoc")
        }

        afterEvaluate {
            extensions.findByType<PublishingExtension>()?.apply {
                repositories {
                    maven {
                        url = uri(if (isReleaseBuild) RELEASE_URL else SNAPSHOT_URL)
                        credentials {
                            username = getSecret("MAVEN_USERNAME")
                            password = getSecret("MAVEN_PASSWORD")
                        }
                    }
                }

                publications.withType<MavenPublication>().configureEach {
                    artifact(emptyJavadocJar.get())

                    pom {
                        name.set(NAME)
                        description.set(DESCRIPTION)
                        url.set(URL)

                        licenses {
                            license {
                                name.set(LICENSE_NAME)
                                url.set(LICENSE_URL)
                                distribution.set(LICENSE_DISTRIBUTION)
                            }
                        }
                        developers {
                            developer {
                                id.set(DEVELOPER_ID)
                                name.set(DEVELOPER_NAME)
                                email.set(DEVELOPER_EMAIL)
                            }
                        }
                        scm { url.set(URL) }
                    }
                }
            }

            extensions.findByType<SigningExtension>()?.apply {
                val publishing = extensions.findByType<PublishingExtension>() ?: return@apply
                val key = getSecret("GPG_KEY").replace("\\n", "\n")
                val password = getSecret("GPG_PASSWORD")

                @Suppress("UnstableApiUsage")
                useInMemoryPgpKeys(key, password)
                sign(publishing.publications)
            }

            tasks.withType<Sign>().configureEach {
                onlyIf { isReleaseBuild }
            }
        }
    }
}

koverMerged {
    enable()
}

val isReleaseBuild: Boolean
    get() = System.getenv("GPG_KEY") != null

fun getSecret(
    key: String,
    default: String = "secret" // these values can not be public
): String = System.getenv(key).let {
    if (it.isNullOrEmpty()) {
        getSecretProperties()?.get(key)?.toString() ?: default
    } else {
        it
    }
}

fun getSecretProperties() = try {
    Properties().apply { load(file("key.properties").inputStream()) }
} catch (e: IOException) {
    logger.debug(e.message, e)
    null
}

object Library {
    const val GROUP = "com.github.submob"
    const val URL = "https://github.com/SubMob/ScopeMob"
    const val NAME = "ScopeMob"
    const val DESCRIPTION = "Set of useful scope and higher-order functions"
    const val DEVELOPER_NAME = "Mustafa Ozhan"
    const val DEVELOPER_ID = "mustafaozhan"
    const val DEVELOPER_EMAIL = "mr.mustafa.ozhan@gmail.com"
    const val LICENSE_NAME = "The Apache Software License, Version 2.0"
    const val LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val LICENSE_DISTRIBUTION = "repo"
    const val RELEASE_URL = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2"
    const val SNAPSHOT_URL = "https://s01.oss.sonatype.org/content/repositories/snapshots"
}

tasks.withType<DependencyUpdatesTask> {
    gradleReleaseChannel = "current"
    rejectVersionIf { candidate.version.isNonStable() }
}

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf(
        "RELEASE",
        "FINAL",
        "GA"
    ).any {
        this.toUpperCase(java.util.Locale.ROOT).contains(it)
    }

    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}
