plugins {
    alias(libs.plugins.gyeonggicurrency.hilt)
    alias(libs.plugins.gyeonggicurrency.android.library)
    id("kotlinx-serialization")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.zibro.gyeonggilocationcurrencyapp.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}


dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)

    testImplementation(libs.kotlinx.coroutines.test)
}