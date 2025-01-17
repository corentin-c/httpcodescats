package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import javax.inject.Inject

class HttpCodesUseCase @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : IHttpCodesUseCase {
	override suspend fun getAllHttpCodes() = httpCodesRepository.getAllHttpCodes()

	override suspend fun filterHttpCodes(newValue: String): List<HttpCode> {
		return httpCodesRepository.getAllHttpCodes().filter { httpCode ->
			httpCode.code.toString().startsWith(newValue)
		}
	}

	override suspend fun getHttpCode(code: Int): HttpCode? {
		return httpCodesRepository.getAllHttpCodes().find {
			it.code == code
		}
	}
}