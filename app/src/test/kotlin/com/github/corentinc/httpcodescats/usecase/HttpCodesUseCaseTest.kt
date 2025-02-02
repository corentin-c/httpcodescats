package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.Test


class HttpCodesUseCaseTest {
    private val httpCodesRepository: IHttpCodesRepository = mockk()

    private val systemUnderTest = HttpCodesUseCase(httpCodesRepository)

    private val httpCode404 = HttpCode(404, "not found", "description 1")
    private val httpCode2 = HttpCode(403, "forbidden", "description 2")
    private val httpCodes = listOf(
        httpCode404,
        httpCode2,
    )

    @Test
    fun `test that getAllHttpCodes should return the result of the repository`() = runTest {
        // Given
        coEvery {
            httpCodesRepository.getAllHttpCodes()
        } returns httpCodes

        // When
        val result = systemUnderTest.getAllHttpCodes()

        // Then
        Assertions.assertThat(result).isEqualTo(httpCodes)
    }

    @Test
    fun `test that filterHttpCodes should filter based on code`() = runTest {
        // When
        val result = systemUnderTest.filterHttpCodes(httpCodes, "404")

        // Then
        Assertions.assertThat(result).isEqualTo(listOf(httpCode404))
    }

    @Test
    fun `test that filterHttpCodes should filter based on name`() = runTest {
        // When
        val result = systemUnderTest.filterHttpCodes(httpCodes, "found")

        // Then
        Assertions.assertThat(result).isEqualTo(listOf(httpCode404))
    }

    @Test
    fun `test that filterHttpCodes should filter based on description`() = runTest {
        // When
        val result = systemUnderTest.filterHttpCodes(httpCodes, "description 2")

        // Then
        Assertions.assertThat(result).isEqualTo(listOf(httpCode2))
    }

    @Test
    fun `test that getHttpCode when the code exists should return the corresponding one`() = runTest {
        // Given
        coEvery {
            httpCodesRepository.getAllHttpCodes()
        } returns httpCodes

        // When
        val result = systemUnderTest.getHttpCode(404)

        // Then
        Assertions.assertThat(result).isEqualTo(httpCode404)
    }

    @Test
    fun `test that getHttpCode when the code doesn't exist should return null`() = runTest {
        // Given
        coEvery {
            httpCodesRepository.getAllHttpCodes()
        } returns httpCodes

        // When
        val result = systemUnderTest.getHttpCode(10000)

        // Then
        Assertions.assertThat(result).isEqualTo(null)
    }

}