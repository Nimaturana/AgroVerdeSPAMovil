plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.agroverdaspados"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.agroverdaspados"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // OkHttp - Cliente HTTP
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Retrofit - Cliente REST
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Coroutines (si no las tienes)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // DataStore - Para guardar tokens
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil - Para cargar imágenes desde URLs (opcional)
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // NAVHOST
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // Dependencia del "ojo" para la contraseña
    implementation("androidx.compose.material:material-icons-extended")
    // Dependencia Accompanist Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.35.1-alpha")

    // Testing - JUnit
    testImplementation("junit:junit:4.13.2")

    // Testing - Coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // Testing - MockK (mocking framework)

    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)
    // Testing - Turbine (para testar StateFlow/Flow)

    testImplementation("app.cash.turbine:turbine:1.0.0")

    // Testing - Core Testing (para InstantTaskExecutorRule)
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Android Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}