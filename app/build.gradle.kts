import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.parcelize)
    alias(libs.plugins.navigation.safeArgsKotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.gundogar.eterationchallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.gundogar.eterationchallenge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://5fc9346b2af77700165ae514.mockapi.io/\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.logging.interceptor)

    // Gson
    implementation (libs.gson)
    implementation (libs.converter.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.test)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Coil
    implementation(libs.coil)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Paging 3
    implementation(libs.paging.runtime)
    testImplementation(libs.androidx.paging.common)

    // Shimmer
    implementation(libs.shimmer)

    // Recycler View
    implementation(libs.androidx.recyclerview)

    // Splash Screen
    implementation(libs.androidx.splashscreen)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Detekt
    detektPlugins(libs.detekt)


}


detekt {
    config.setFrom(file("$rootDir/detekt/detektConfig.yml"))
    source.from(files("src/main/kotlin", "src/test/kotlin"))
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}