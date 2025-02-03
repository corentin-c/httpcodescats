package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.unconfinedTestDispatcherProvider
import com.github.corentinc.httpcodescats.usecase.IHttpCodesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HttpCodesListViewModelTest {
	private val dispatcherProvider = unconfinedTestDispatcherProvider()
	private val httpCodesUseCase: IHttpCodesUseCase = mockk()
	private lateinit var systemUnderTest: HttpCodesListViewModel

	private val httpCode404 = HttpCode(404, "not found", "description 1")
	private val httpCode2 = HttpCode(403, "forbidden", "description 2")
	private val httpCodes = listOf(
		httpCode404,
		httpCode2,
	)

	@BeforeEach
	fun before() {
		coEvery {
			httpCodesUseCase.getAllHttpCodes()
		} returns httpCodes

		systemUnderTest = HttpCodesListViewModel(dispatcherProvider, httpCodesUseCase)
	}

	@Test
	fun `test onStart should load http codes from the useCase and set loading to false`() =
		runTest {
			// When
			val result = systemUnderTest.uiState.value

			// Then
			Assertions.assertThat(result.httpCodes).isEqualTo(httpCodes)
			Assertions.assertThat(result.isLoading).isEqualTo(false)
		}

	@Test
	fun `test onFilterChanged should update uiState using filter of useCase and filter`() =
		runTest {
			// Given
			val expectedFilteredCodes = listOf(httpCode2)
			val filter = "filter"
			coEvery {
				httpCodesUseCase.filterHttpCodes(httpCodes, filter)
			} returns expectedFilteredCodes

			// When
			systemUnderTest.onFilterChanged(filter)
			val result = systemUnderTest.uiState.value

			// Then
			Assertions.assertThat(result.httpCodes).isEqualTo(expectedFilteredCodes)
			Assertions.assertThat(result.filter).isEqualTo(filter)
		}
}