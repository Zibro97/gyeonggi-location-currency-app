import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.google.service)
}

val localProperties  = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    namespace = "com.zibro.gyeonggilocationcurrencyapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.zibro.gyeonggilocationcurrencyapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val naverMapClientKey = localProperties.getProperty("NAVER_MAP_CLIENT_KEY")
        manifestPlaceholders["naver_map_client_key"] = naverMapClientKey
        resValue("string", "naver_map_client_key", "\"${naverMapClientKey}\"")
        buildConfigField("String", "naver_map_client_key", "\"${naverMapClientKey}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // kotlinx.serialization
    implementation(libs.kotlinx.serialization.json)

    // mvi framework
    implementation(libs.mavericks.compose)

    // hilt di
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // naver map
    implementation(libs.map.sdk)

    // admob
    implementation(libs.play.services.ads)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // location
    implementation(libs.play.services.location)
}