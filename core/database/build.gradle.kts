plugins {
    alias(libs.plugins.gyeonggicurrency.android.library)
    alias(libs.plugins.gyeonggicurrency.android.library.jacoco)
    alias(libs.plugins.gyeonggicurrency.android.room)
    alias(libs.plugins.gyeonggicurrency.hilt)
}

android {
    namespace = "com.zibro.gyeonggilocationcurrencyapp.core.database"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {

    implementation(libs.core.ktx)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation("app.cash.turbine:turbine:1.0.0")
}