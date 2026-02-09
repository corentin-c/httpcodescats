import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.hilt)
	alias(libs.plugins.google.services)
	alias(libs.plugins.firebase.crashlytics)
	alias(libs.plugins.google.devtools.ksp)
}

android {
	signingConfigs {
		create("release") {
			val keystoreProps = Properties()
			keystoreProps.load(FileInputStream(file("keystore.properties")))
			keyAlias = keystoreProps["keyAlias"] as String
			keyPassword = keystoreProps["keyPassword"] as String
			storePassword = keystoreProps["storePassword"] as String
			storeFile = file("keystore.jks")
		}
	}
	namespace = "com.github.corentinc.httpcodescats"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.github.corentinc.httpcodescats"
		minSdk = 26
		targetSdk = 36
		versionCode = 6
		versionName = "1.1.2"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			signingConfig = signingConfigs.getByName("release")
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}

	kotlin {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_21
		}
	}

	testOptions {
		unitTests.all {
			it.useJUnitPlatform()
		}
	}

	buildFeatures {
		compose = true
	}

	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.navigation.compose)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	implementation(libs.hilt.android)
	implementation(libs.androidx.hilt.navigation.compose)
	ksp(libs.hilt.android.compiler)
	implementation(libs.glide)
	implementation(libs.customTabs)
	implementation(libs.material.icons.core)

	// Firebase
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)
	implementation(libs.firebase.firestore)

	// Tests
	testImplementation(libs.mockk)
	testImplementation(platform(libs.junit.bom))
	testImplementation(libs.junit.jupiter)
	testRuntimeOnly(libs.junit.platform.launcher)
	testImplementation(libs.assertJCore)
	testImplementation(libs.kotlinx.coroutines.test)

}