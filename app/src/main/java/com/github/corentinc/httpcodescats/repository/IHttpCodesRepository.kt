package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode

interface IHttpCodesRepository {
	suspend fun getAllHttpCodes(): List<HttpCode>
}