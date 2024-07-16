import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.apollo)
    alias(libs.plugins.room)

}

kotlin {

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class) compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            linkerOpts.add("-lsqlite3") // add sqlite
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

            // Fixes RoomDB unresolved reference 'instantiateImpl' in iosMain
//            kotlin.srcDirs("build/generated/ksp/metadata")
        }
        commonMain {
            kotlin.srcDirs("build/generated/ksp/metadata")
        }
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.koin.core)
            implementation(libs.apollo.runtime)
            implementation(libs.apollo.normalized.cache)
            implementation(libs.apollo.normalized.cache.sqlite)
            api(libs.androidx.datastore.preferences.core)
            api(libs.androidx.datastore.core.okio)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

    }

    task("testClasses")

    apollo {
        // instruct the compiler to generate Kotlin models

        service("countries") {
            generateKotlinModels.set(true)
            sourceFolder.set("../kotlin/com/jetbrains/kmpapp/graphql")
            packageName.set("com.jetbrains.kmpapp.graphql")
        }

    }

}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
//    ksp(libs.androidx.room.compiler)
//    add("kspAndroid", libs.androidx.room.compiler)
    add("kspCommonMainMetadata", libs.androidx.room.compiler)
//    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
//    add("kspIosX64", libs.androidx.room.compiler)
//    add("kspIosArm64", libs.androidx.room.compiler)


    //JVM Target
//    kspJvm(libs.androidx.room.compiler)

    // Android Target
//    kspAndroid(libs.androidx.room.compiler)

    // JS Target (for both legacy and IR compilers)
//    kspJs(libs.androidx.room.compiler)

    // iOS Targets
//    kspIosArm64(libs.androidx.room.compiler)
//    kspIosX64(libs.androidx.room.compiler)
//    kspIosSimulatorArm64(libs.androidx.room.compiler)

    // Other potential targets (adjust as per your project)
//     kspLinuxX64(libs.androidx.room.compiler)
//     kspMacOSArm64(libs.androidx.room.compiler)
//     kspMacOSX64(libs.androidx.room.compiler)
//     kspMingwX64(libs.androidx.room.compiler)
//     kspWasmJs(libs.androidx.room.compiler)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata" ) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "com.jetbrains.kmpapp.shared"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = 24
    }
}
