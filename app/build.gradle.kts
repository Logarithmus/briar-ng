plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization") version "1.4.20"
}

android.sourceSets.all {
    java.srcDir("src/$name/kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")
    defaultConfig {
        applicationId = "dev.logarithmus.briarng"
        minSdkVersion(19)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
			isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.20")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}
