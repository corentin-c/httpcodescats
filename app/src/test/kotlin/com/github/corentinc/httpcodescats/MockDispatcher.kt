@file:OptIn(ExperimentalCoroutinesApi::class)

package com.github.corentinc.httpcodescats

import com.github.corentinc.httpcodescats.coroutines.IDispatcherProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain

fun unconfinedTestDispatcherProvider(testCoroutineScheduler: TestCoroutineScheduler = TestCoroutineScheduler()): IDispatcherProvider =
	mockk {
		val testDispatcher = UnconfinedTestDispatcher(testCoroutineScheduler)
		Dispatchers.setMain(testDispatcher)
		every { default() } returns testDispatcher
		every { io() } returns testDispatcher
		every { main() } returns testDispatcher
		every { unconfined() } returns testDispatcher
	}