import java.io.FileInputStream
import java.util.Properties

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.compose.compiler)
	alias(libs.plugins.hilt)
	alias(libs.plugins.google.services)
	alias(libs.plugins.firebase.crashlytics)
	id("kotlin-kapt")
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
	compileSdk = 35

	defaultConfig {
		applicationId = "com.github.corentinc.httpcodescats"
		minSdk = 26
		targetSdk = 35
		versionCode = 2
		versionName = "1.0.0"

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
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
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
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	implementation(libs.hilt.android)
	annotationProcessor(libs.hilt.android.compiler)
	implementation(libs.androidx.hilt.navigation.compose)
	kapt(libs.hilt.android.compiler)
	implementation(libs.glide)
	implementation(libs.customTabs)

	// Firebase
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.crashlytics)

}

kapt {
	correctErrorTypes = true
}