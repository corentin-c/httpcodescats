package com.github.corentinc.httpcodescats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.corentinc.httpcodescats.ui.screens.httpcodedetails.HttpCodeDetailsScreen
import com.github.corentinc.httpcodescats.ui.screens.httpcodeslist.HttpCodeListScreen


private const val HTTP_CODE_LIST_SCREEN = "httpCodeListScreen"
private const val HTTP_CODE_DETAILS_SCREEN = "httpCodeDetailsScreen"
private const val HTTP_CODE_ARGUMENT = "httpCodeArgument"

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
			HttpCodeListScreen(onHttpCodeClicked = { code: Int ->
				navigationController.navigate("$HTTP_CODE_DETAILS_SCREEN/$code")
			})
		}

		composable(
			route = "$HTTP_CODE_DETAILS_SCREEN/{$HTTP_CODE_ARGUMENT}",
			arguments = listOf(
				navArgument(HTTP_CODE_ARGUMENT) {
					type = NavType.IntType
					nullable = false
				},
			),
			enterTransition = enterTransition,
			popEnterTransition = popEnterTransition,
			exitTransition = exitTransition
		) { backStackEntry ->
			val code = backStackEntry.arguments?.getInt(HTTP_CODE_ARGUMENT)!!
			HttpCodeDetailsScreen(code)
		}
	}
}