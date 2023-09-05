
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.vimlesh.jokesunlimit"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.vimlesh.jokesunlimit"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        viewBinding.enable = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation ("io.insert-koin:koin-android:3.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    // To use Kotlin annotation processing tool (kapt)
    // To use Kotlin Symbol Processing (KSP)
    kapt("androidx.room:room-compiler:2.5.2")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    testImplementation ("io.insert-koin:koin-test:3.4.0")
    testImplementation ("io.insert-koin:koin-test-junit4:3.4.0")
    testImplementation ("org.mockito:mockito-inline:4.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}