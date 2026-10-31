plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "org.ianus.revmeet"
    compileSdk {
        version = release(37)
    }
    /// enabled view binding
    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "org.ianus.revmeet"
        minSdk = 30
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.timber)
    implementation(libs.picasso)
    implementation(libs.play.services.maps)
    implementation(libs.gson)

}