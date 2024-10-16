package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode

interface IHttpCodesUseCase {
	fun getAllHttpCodes(): List<HttpCode>
	fun filterHttpCodes(newValue: String): List<HttpCode>
	fun getHttpCode(code: Int): HttpCode?
}