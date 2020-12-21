buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("http://dl.bintray.com/amulyakhare/maven")
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("http://dl.bintray.com/amulyakhare/maven")
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
