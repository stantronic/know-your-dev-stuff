plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "space.stanton.know.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "space.stanton.know.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.android.compat)
}