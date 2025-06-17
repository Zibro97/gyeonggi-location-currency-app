package com.zibro.convention

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply("org.jetbrains.kotlin.jvm")

            tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
                }
            }
        }
    }
}