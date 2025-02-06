package com.github.corentinc.httpcodescats.ui.screens.httpcodedetails

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.unconfinedTestDispatcherProvider
import com.github.corentinc.httpcodescats.usecase.IHttpCodesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HttpCodeDetailViewModelTest {
	private val dispatcherProvider = unconfinedTestDispatcherProvider()
	private val httpCodesUseCase: IHttpCodesUseCase = mockk()

	private val systemUnderTest = HttpCodeDetailViewModel(dispatcherProvider, httpCodesUseCase)

	@Test
	fun `test getHttpCodeDetails should return the result from the useCase and set loading to false`() = runTest {
		// Given
		val httpCode = 404
		val expectedResult = mockk<HttpCode>()
		coEvery {
			httpCodesUseCase.getHttpCode(httpCode)
		} returns expectedResult

		// When
		systemUnderTest.getHttpCodeDetails(httpCode)
		val result = systemUnderTest.uiState.value

		// Then
		Assertions.assertThat(result.httpCode).isEqualTo(expectedResult)
		Assertions.assertThat(result.isLoading).isFalse()
	}
}