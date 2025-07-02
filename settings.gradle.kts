pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.zibro.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://repository.map.naver.com/archive/maven")
    }
}

// 프로젝트 간 종속성을 더 안전하게 사용할 수 있도록 활성화
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "GyeonggiLocationCurrencyApp"
include(":app")
include(":feature:map")
include(":network")
