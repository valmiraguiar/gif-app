plugins {
    kotlin("jvm")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    // Retrofit + OkHttp
    api(libs.retrofit)
    api(libs.retrofit.converter.gson)
    api(libs.okhttp.logging.interceptor)
    api(libs.androidx.paging.common)
}
