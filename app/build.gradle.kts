import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.bahn_zeitkarten_tracker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.bahn_zeitkarten_tracker"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


    buildFeatures {
        viewBinding = true
        //jetpack compose
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}
dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //jetpack compose
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation(libs.play.services.maps)
    implementation(libs.ui)

    implementation ("com.jjoe64:graphview:4.2.2")
    implementation(libs.ui.text)

    //mapsforge
    implementation("org.mapsforge:mapsforge-core:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-map:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-map-reader:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-themes:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-map-android:0.25.0@jar")
    implementation("com.caverock:androidsvg:1.4")
    implementation("org.mapsforge:mapsforge-core:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-poi:0.25.0@jar")
    implementation("org.mapsforge:mapsforge-poi-android:0.25.0@jar")

    debugImplementation("androidx.compose.ui:ui-tooling")


    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}