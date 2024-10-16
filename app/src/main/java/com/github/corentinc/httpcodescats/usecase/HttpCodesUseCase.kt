package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import javax.inject.Inject

class HttpCodesUseCase @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : IHttpCodesUseCase {
	override fun getAllHttpCodes() = httpCodesRepository.getAllHttpCodes()

	override fun filterHttpCodes(newValue: String): List<HttpCode> {
		return httpCodesRepository.getAllHttpCodes().filter { httpCode ->
			httpCode.code.toString().startsWith(newValue)
		}
	}

	override fun getHttpCode(code: Int): HttpCode? {
		return httpCodesRepository.getAllHttpCodes().find {
			it.code == code
		}
	}
}