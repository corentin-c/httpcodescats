package com.github.corentinc.httpcodescats.repository

import com.github.corentinc.httpcodescats.model.HttpCode
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val HTTP_CODES_COLLECTION = "httpcodes"
private const val NAME_FILED = "name"
private const val DESCRIPTION_FILED = "description"

class HttpCodesRepositoryTest {
	private val systemUnderTest = HttpCodesRepository()

	private val id = "404"
	private val name = "name"
	private val description = "description"

	@BeforeEach
	fun before() {
		mockkStatic("kotlinx.coroutines.tasks.TasksKt")
		mockkStatic(FirebaseFirestore::class)
	}

	@Test
	fun `test that getAllHttpCodes should return the result of the repository`() = runTest {
		// Given
		val mockFirestore = mockk<FirebaseFirestore>()
		every { FirebaseFirestore.getInstance() } returns mockFirestore

		val documentSnapshot = mockk<DocumentSnapshot>()
		every {
			documentSnapshot.id
		} returns id
		every {
			documentSnapshot.getString(NAME_FILED)
		} returns name
		every {
			documentSnapshot.getString(DESCRIPTION_FILED)
		} returns description
		val cinSnapshot = mockk<QuerySnapshot> {
			every {
				documents
			} returns listOf(documentSnapshot)
		}
		coEvery {
			mockFirestore.collection(HTTP_CODES_COLLECTION)
				.get()
				.await()
		} returns cinSnapshot

		// When
		val result = systemUnderTest.getAllHttpCodes()

		// Then
		Assertions.assertThat(result).isEqualTo(listOf(HttpCode(404, name, description)))
	}
}