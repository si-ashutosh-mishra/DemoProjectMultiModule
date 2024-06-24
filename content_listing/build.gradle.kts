import org.jetbrains.kotlin.kapt3.base.Kapt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.hilt)
    id("kotlin-kapt")
    id("kotlin-android")
}

android {
    namespace = "com.example.content_listing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }

}

dependencies {

    implementation(project(":base"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.runtime.livedata)

    //hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.hilt.compiler)

    //network
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.gsonconverter)

    implementation(libs.retrofit.okHttp)
    implementation(libs.retrofit.okHttp.interceptor)
}