package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import javax.inject.Inject

class HttpCodesUseCase @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : IHttpCodesUseCase {
	override suspend fun getAllHttpCodes() = httpCodesRepository.getAllHttpCodes()

	override suspend fun filterHttpCodes(filter: String): List<HttpCode> {
		return httpCodesRepository.getAllHttpCodes().filter { httpCode ->
			httpCode.code.toString().contains(filter, ignoreCase = true)
					|| httpCode.name.contains(filter, ignoreCase = true)
					|| httpCode.description.contains(filter, ignoreCase = true)
		}
	}

	override suspend fun getHttpCode(code: Int): HttpCode? {
		return httpCodesRepository.getAllHttpCodes().find {
			it.code == code
		}
	}
}