plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.muhammhassan.jkblc_starter_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.muhammhassan.jkblc_starter_project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")

        //TODO 4: Masukkan nilai Field Api Key dibawah ini
        buildConfigField("String", "API_KEY", "\"410ba3e7ec4a43de9ad8afdd39de4dac\"")
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

    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.bumptech.glide)
    ksp(libs.bumptech.glide.processor)


    //TODO 3: Implement library activity-ktx, retrofit, dan gson
    implementation(libs.androidx.activity.ktx)
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.gson.converter)
    implementation(libs.squareup.logging.interceptor)
}