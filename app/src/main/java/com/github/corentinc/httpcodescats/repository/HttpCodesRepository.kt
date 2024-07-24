package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode
import javax.inject.Inject

class HttpCodesRepository @Inject constructor() : IHttpCodesRepository {
	override fun getHttpCodes(): List<HttpCode> {
		return HttpCode.httpCodes
	}


}