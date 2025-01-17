package com.github.corentinc.httpcodescats.usecase

import com.github.corentinc.httpcodescats.model.HttpCode

interface IHttpCodesUseCase {
	suspend fun getAllHttpCodes(): List<HttpCode>
	suspend fun filterHttpCodes(filter: String): List<HttpCode>
	suspend fun getHttpCode(code: Int): HttpCode?
}