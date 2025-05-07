import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.buildKonfig)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your multiplatform dependencies here
            implementation(libs.koin)
            implementation(libs.ktor.client)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.serialization.json)

            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.koin.android)
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.compose.material3)
            implementation(libs.compose.navigation)
            implementation(libs.androidx.activity.compose)
            implementation(libs.firebase.analytics)
            implementation(libs.firebase.auth)
            implementation(libs.koin.android)
            implementation(libs.koin.compose)
            implementation(libs.koin.android.compat)
            dependencies {
                debugImplementation(libs.compose.ui.tooling)
            }
        }
    }
}


buildkonfig {
    packageName = "space.stanton.know"
    defaultConfigs {
        val localProperties = Properties().apply {
            rootProject.file("local.properties").reader().use(::load)
        }

        buildConfigField(
            FieldSpec.Type.STRING,
            "QUIZAPI_KEY",
            checkNotNull(
                "${localProperties["space.stanton.know.quizapi.token"]}"
            ) {
                "No QuizApi key provided"
            }
        )
    }
}

android {
    namespace = "space.stanton.know"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
