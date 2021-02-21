/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    `maven-publish`
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(ClassPaths.kotlinGradlePlugin)
    }
}

val props = Properties()

try {
    props.load(rootProject.file("key.properties").inputStream())
} catch (e: Exception) {
    // keys are private and can not be committed to git
}

allprojects {

    group = Library.libraryGroup
    version = Library.libraryVersion

    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    val emptyJavadocJar by tasks.registering(Jar::class) {
        archiveClassifier.set("javadoc")
    }

    afterEvaluate {

        extensions.findByType<PublishingExtension>()?.apply {

            publications {
                create<MavenPublication>("maven") {
                    groupId = Library.libraryGroup
                    artifactId = Library.libraryArtifact
                    version = Library.libraryVersion
                }
            }

            repositories {
                maven {

                    url = uri(
                        if (isReleaseBuild) {
                            "https://oss.sonatype.org/service/local/staging/deploy/maven2"
                        } else {
                            "https://oss.sonatype.org/content/repositories/snapshots"
                        }
                    )
                    credentials {
                        username = props["sonar.username"]?.toString()
                        password = props["sonar.password"]?.toString()
                    }
                }
            }

            publications.withType<MavenPublication>().configureEach {
                artifact(emptyJavadocJar.get())

                pom {
                    name.set(Library.libraryName)
                    description.set(Library.libraryDescription)
                    url.set(Library.libraryUrl)

                    licenses {
                        license {
                            name.set(Library.licenseName)
                            url.set(Library.licenseUrl)
                            distribution.set(Library.licenseDistribution)
                        }
                    }
                    developers {
                        developer {
                            id.set(Library.developerId)
                            name.set(Library.developerName)
                            email.set(Library.developerEmail)
                        }
                    }
                    scm {
                        url.set(Library.libraryUrl)
                    }
                }
            }
        }

        extensions.findByType<SigningExtension>()?.apply {
            val publishing = extensions.findByType<PublishingExtension>() ?: return@apply

            val signingKey = props["gpg.key"]?.toString()?.replace("\\n", "\n")
            val signingPassword = props["gpg.password"]?.toString()
            useInMemoryPgpKeys(signingKey, signingPassword)

            sign(publishing.publications)
        }

        tasks.withType<Sign>().configureEach {
            onlyIf { isReleaseBuild }
        }
    }
}

object Library {
    const val libraryGroup = "com.github.sub-mob"
    const val libraryArtifact = "scopemob"
    const val libraryVersion = "2.0.0"

    const val publishPlugin = "maven-publish"

    const val libraryUrl = "https://github.com/SUB-MOB/scopemob"
    const val libraryName = "Scope Mob"
    const val libraryDescription = "Set of useful scope and Higher-order functions"

    const val developerName = "Mustafa Ozhan"
    const val developerId = "mustafaozhan"
    const val developerEmail = "mr.mustafa.ozhan@gmail.com"

    const val licenseName = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val licenseDistribution = "repo"
}

val isReleaseBuild: Boolean
    get() = props.containsKey("gpg.key")
