package com.github.corentinc.httpcodescats.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() : IDispatcherProvider {

	override fun default() = Dispatchers.Default

	override fun io() = Dispatchers.IO

	override fun main() = Dispatchers.Main

	override fun unconfined() = Dispatchers.Unconfined

}
