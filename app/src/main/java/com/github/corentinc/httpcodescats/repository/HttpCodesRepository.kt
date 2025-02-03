package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val HTTP_CODES_COLLECTION = "httpcodes"
private const val NAME_FILED = "name"
private const val DESCRIPTION_FILED = "description"

class HttpCodesRepository @Inject constructor() : IHttpCodesRepository {

	override suspend fun getAllHttpCodes(): List<HttpCode> {
		val firestoreInstance = FirebaseFirestore.getInstance()
		return firestoreInstance
			.collection(HTTP_CODES_COLLECTION)
			.get()
			.await()
			.documents.mapNotNull { document ->
				val code = document.id.toInt()
				val name = document.getString(NAME_FILED)!!
				val description = document.getString(DESCRIPTION_FILED)!!
				HttpCode(
					code = code,
					name = name,
					description = description
				)
			}
	}
}