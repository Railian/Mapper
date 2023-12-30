import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import java.util.Properties

plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
    signing
}

group = "io.github.railian.mapper"

val isSnapshot = false
version = "0.1.4" + if (isSnapshot) "-SNAPSHOT" else ""

kotlin {
    explicitApi = ExplicitApiMode.Strict
    compilerOptions {
        jvmTarget = JvmTarget.JVM_1_8
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

Properties().apply { load(project.rootProject.file("local.properties").reader()) }
    .onEach { project.extraProperties[it.key.toString()] = it.value }

publishing {
    publications {
        create<MavenPublication>("jvm") {
            artifactId = "mapper"
            from(components["java"])
            pom {
                name.set("$groupId:$artifactId")
                packaging = "jar"
                url.set("https://github.com/Railian/Mapper")
                description.set("Creating an easy mappers from one entity to another")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    connection.set("scm:https://github.com/Railian/Mapper.git")
                    developerConnection.set("scm:git@github.com:Railian/Mapper.git")
                    url.set("https://github.com/Railian/Mapper")
                }
                developers {
                    developer {
                        id.set("YevhenRailian")
                        name.set("Yevhen Railian")
                        email.set("yevhen.railian.v@gmail.com")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Railian/Mapper")
                credentials {
                    username = project.properties["githubActor"].toString()
                    password = project.properties["githubToken"].toString()
                }
            }
            maven {
                name = "Sonatype"
                url = when {
                    isSnapshot -> "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    else -> "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }.let(::uri)
                credentials {
                    username = project.properties["sonatypeUsername"].toString()
                    password = project.properties["sonatypePassword"].toString()
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["jvm"])
}

dependencies {
    testImplementation(libs.kotlin.test)
}
