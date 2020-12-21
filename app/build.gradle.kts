plugins{
    id("com.android.application")
    kotlin("android")
    // Annotation processor for Kotlin
    kotlin("kapt")
}

android.sourceSets.all {
    java.srcDir("src/$name/kotlin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.2")
    defaultConfig {
        applicationId = "dev.logarithmus.p2pdroid"
        minSdkVersion(16)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.1.0"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.2.1")

    implementation("androidx.room:room-runtime:2.2.6")
    kapt("androidx.room:room-compiler:2.2.6")

    implementation("androidx.lifecycle:lifecycle-runtime:2.2.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.2.0")

    implementation("com.timehop.stickyheadersrecyclerview:library:0.4.3@aar")
    implementation("com.amulyakhare:com.amulyakhare.textdrawable:1.0.1")
    implementation("com.github.chrisbanes:PhotoView:2.3.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.github.jkwiecien:EasyImage:2.1.1")
    implementation("io.github.kobakei:ratethisapp:1.2.0")
    implementation("me.priyesh:chroma:1.0.2")

    implementation("org.koin:koin-android:1.0.2")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")
}
