package com.github.corentinc.httpcodescats.model

data class HttpCode(val code: Int, val name: String, val description: String) {
	val imageUrl = "https://http.cat/$code.jpg"
	val source = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/$code"
}