package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode

interface IHttpCodesRepository {
	fun getAllHttpCodes(): List<HttpCode>
}