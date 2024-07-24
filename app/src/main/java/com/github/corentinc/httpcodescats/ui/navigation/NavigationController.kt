package com.github.corentinc.httpcodescats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.corentinc.httpcodescats.ui.screens.HttpCodeListScreen


private const val HTTP_CODE_LIST_SCREEN = "httpCodeListScreen"

@Composable
fun NavigationController() {
	val navigationController = rememberNavController()


	NavHost(navController = navigationController, startDestination = HTTP_CODE_LIST_SCREEN) {
		composable(
			route = HTTP_CODE_LIST_SCREEN,
			enterTransition = enterTransition,
			popEnterTransition = popEnterTransition,
			exitTransition = exitTransition
		) {
			HttpCodeListScreen()
		}
	}
}