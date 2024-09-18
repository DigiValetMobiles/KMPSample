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
            api(libs.androidx.datastore.preferences)
            api(libs.androidx.datastore.core)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }

    }

    task("testClasses")

    apollo {
        service("countries") {
        // instruct the compiler to generate Kotlin models
            generateKotlinModels.set(true)
            sourceFolder.set("../kotlin/com/paragon/kmpapp/graphql")
            packageName.set("com.paragon.kmpapp.graphql")
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

}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata" ) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "com.paragon.kmpapp.shared"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = 24
    }
}
