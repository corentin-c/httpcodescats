package com.github.corentinc.httpcodescats.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {

	fun default(): CoroutineDispatcher

	fun io(): CoroutineDispatcher

	fun main(): CoroutineDispatcher

	fun unconfined(): CoroutineDispatcher

}
