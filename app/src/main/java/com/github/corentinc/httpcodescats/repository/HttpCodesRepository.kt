package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode
import javax.inject.Inject

class HttpCodesRepository @Inject constructor() : IHttpCodesRepository {
	override fun getAllHttpCodes(): List<HttpCode> {
		return HttpCode.httpCodes
	}

	override fun getHttpCode(code: Int): HttpCode? {
		return HttpCode.httpCodes.find {
			it.code == code
		}
	}
}