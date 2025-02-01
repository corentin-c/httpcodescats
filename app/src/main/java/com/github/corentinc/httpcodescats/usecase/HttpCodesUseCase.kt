package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import javax.inject.Inject

class HttpCodesUseCase @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : IHttpCodesUseCase {
	override suspend fun getAllHttpCodes() = httpCodesRepository.getAllHttpCodes()

	override fun filterHttpCodes(originalList: List<HttpCode>, filter: String): List<HttpCode> {
		return originalList.filter { httpCode ->
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