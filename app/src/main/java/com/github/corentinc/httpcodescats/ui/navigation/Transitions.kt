package com.github.corentinc.httpcodescats.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

private const val TWO_HUNDRED_MILLISECONDS_DURATION = 300
private const val THREE_HUNDRED_MILLISECONDS_DURATION = 300

val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) = {
	slideIntoContainer(
		towards = AnimatedContentTransitionScope.SlideDirection.Left,
		animationSpec = tween(THREE_HUNDRED_MILLISECONDS_DURATION)
	)
}

val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
	{
		slideIntoContainer(
			towards = AnimatedContentTransitionScope.SlideDirection.Right,
			animationSpec = tween(THREE_HUNDRED_MILLISECONDS_DURATION)
		)
	}

val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) = {
	fadeOut(tween(TWO_HUNDRED_MILLISECONDS_DURATION))
}