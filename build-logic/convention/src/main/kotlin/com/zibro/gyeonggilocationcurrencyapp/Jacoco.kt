package com.zibro.gyeonggilocationcurrencyapp

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import com.android.build.api.variant.SourceDirectories
import org.gradle.api.Project
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.Locale

// 제외할 파일 패턴
private val coverageExclusions = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*_Hilt*.class",
    "**/Hilt_*.class",
)

// 문자열의 첫 글자를 대문자로 변환
private fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

internal fun Project.configureJacoco(
    androidComponentsExtension: AndroidComponentsExtension<*, *, *>,
) {
    // Jacoco 플러그인 적용
    configure<JacocoPluginExtension> {
        toolVersion = libs.findVersion("jacoco").get().toString()
    }

    androidComponentsExtension.onVariants { variant ->
        val myObjFactory = project.objects
        val buildDir = layout.buildDirectory.get().asFile
        val allJars: ListProperty<RegularFile> = myObjFactory.listProperty(RegularFile::class.java)
        val allDirectories: ListProperty<Directory> =
            myObjFactory.listProperty(Directory::class.java)
        val reportTask =
            tasks.register(
                "create${variant.name.capitalize()}CombinedCoverageReport",
                JacocoReport::class,
            ) {

                classDirectories.setFrom(
                    allJars,
                    allDirectories.map { dirs ->
                        dirs.map { dir ->
                            myObjFactory.fileTree().setDir(dir).exclude(coverageExclusions)
                        }
                    },
                )
                reports {
                    xml.required = true
                    html.required = true
                }

                fun SourceDirectories.Flat?.toFilePaths(): Provider<List<String>> = this
                    ?.all
                    ?.map { directories -> directories.map { it.asFile.path } }
                    ?: provider { emptyList() }
                sourceDirectories.setFrom(
                    files(
                        variant.sources.java.toFilePaths(),
                        variant.sources.kotlin.toFilePaths()
                    ),
                )

                executionData.setFrom(
                    project.fileTree("$buildDir/outputs/unit_test_code_coverage/${variant.name}UnitTest")
                        .matching { include("**/*.exec") },

                    project.fileTree("$buildDir/outputs/code_coverage/${variant.name}AndroidTest")
                        .matching { include("**/*.ec") },
                )
            }


        variant.artifacts.forScope(ScopedArtifacts.Scope.PROJECT)
            .use(reportTask)
            .toGet(
                ScopedArtifact.CLASSES,
                { _ -> allJars },
                { _ -> allDirectories },
            )
    }

    tasks.withType<Test>().configureEach {
        configure<JacocoTaskExtension> {
            isIncludeNoLocationClasses = true

            excludes = listOf("jdk.internal.*")
        }
    }
}