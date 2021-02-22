/*
 * Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */

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

allprojects {

    with(Library) {

        group = libraryGroup
        version = libraryVersion

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
                repositories {
                    maven {
                        url = uri(if (isReleaseBuild) releaseUrl else snapshotUrl)
                        credentials {
                            username = System.getenv("MAVEN_USERNAME")?.toString()
                            password = System.getenv("MAVEN_PASSWORD")?.toString()
                        }
                    }
                }

                publications.withType<MavenPublication>().configureEach {
                    artifact(emptyJavadocJar.get())

                    pom {
                        name.set(libraryName)
                        description.set(libraryDescription)
                        url.set(libraryUrl)

                        licenses {
                            license {
                                name.set(licenseName)
                                url.set(licenseUrl)
                                distribution.set(licenseDistribution)
                            }
                        }
                        developers {
                            developer {
                                id.set(developerId)
                                name.set(developerName)
                                email.set(developerEmail)
                            }
                        }
                        scm { url.set(libraryUrl) }
                    }
                }
            }

            extensions.findByType<SigningExtension>()?.apply {
                val publishing = extensions.findByType<PublishingExtension>() ?: return@apply
                val key = System.getenv("GPG_KEY")?.toString()?.replace("\\n", "\n")
                val password = System.getenv("GPG_PASSWORD")?.toString()
                useInMemoryPgpKeys(key, password)
                sign(publishing.publications)
            }

            tasks.withType<Sign>().configureEach {
                onlyIf { isReleaseBuild }
            }
        }
    }
}

val isReleaseBuild: Boolean
    get() = System.getenv("GPG_KEY") != null

object Library {
    const val libraryGroup = "com.github.sub-mob"
    const val libraryArtifact = "scopemob"
    const val libraryVersion = "2.0.1"

    const val publishPlugin = "maven-publish"

    const val libraryUrl = "https://github.com/SUB-MOB/scopemob"
    const val libraryName = "Scope Mob"
    const val libraryDescription = "Set of useful scope and higher-order functions"

    const val developerName = "Mustafa Ozhan"
    const val developerId = "mustafaozhan"
    const val developerEmail = "mr.mustafa.ozhan@gmail.com"

    const val licenseName = "The Apache Software License, Version 2.0"
    const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
    const val licenseDistribution = "repo"

    const val releaseUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    const val snapshotUrl = "https://oss.sonatype.org/content/repositories/snapshots"
}
