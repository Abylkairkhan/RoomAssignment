import com.android.build.api.dsl.Packaging
import org.jetbrains.kotlin.gradle.plugin.kotlinToolingVersion

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.roomassignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.roomassignment"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:$rootProject.appCompatVersion")
    implementation ("androidx.activity:activity-ktx:$rootProject.activityVersion")

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation ("androidx.room:room-ktx:$rootProject.roomVersion")
    kapt ("androidx.room:room-compiler:$rootProject.roomVersion")
    androidTestImplementation ("androidx.room:room-testing:$rootProject.roomVersion")

    // Lifecycle components
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycleVersion")

    // Kotlin components
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines")

    // UI
    implementation ("androidx.constraintlayout:constraintlayout:$rootProject.constraintLayoutVersion")
    implementation ("com.google.android.material:material:$rootProject.materialVersion")

    // Testing
    testImplementation ("junit:junit:$rootProject.junitVersion")
    androidTestImplementation ("androidx.arch.core:core-testing:$rootProject.coreTestingVersion")
    androidTestImplementation ("androidx.test.ext:junit:$rootProject.androidxJunitVersion")
}