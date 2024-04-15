plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.allopen)
    alias(libs.plugins.noarg)
    alias(libs.plugins.kotlin.kapt)
    //alias(libs.plugins.ksp)
}

android {
    namespace = "com.android.hq.wanandroidkotlin"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.hq.wanandroidkotlin"
        minSdk = 28
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
}

allOpen{
    annotation("com.android.hq.wanandroidkotlin.utils.OpenDataClass")
}

noArg{
    annotation("com.android.hq.wanandroidkotlin.utils.OpenDataClass")
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.log)
    implementation(libs.lifecycle.viewmode)
    //implementation(libs.google.material)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    //ksp(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
}