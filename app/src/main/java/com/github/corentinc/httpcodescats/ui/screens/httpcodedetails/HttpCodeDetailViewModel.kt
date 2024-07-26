package com.github.corentinc.httpcodescats.ui.screens.httpcodedetails

import androidx.lifecycle.ViewModel
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HttpCodeDetailViewModel @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : ViewModel() {

	private val uiStateFlow = MutableStateFlow(UiState())
	val uiState: StateFlow<UiState> = uiStateFlow.asStateFlow()

	fun getHttpCodeDetails(code: Int) {
		val httpCode = httpCodesRepository.getHttpCode(code)
		uiStateFlow.update {
			it.copy(
				isLoading = false,
				httpCode = httpCode
			)
		}
	}

	data class UiState(
		var isLoading: Boolean = true,
		var httpCode: HttpCode? = null
	)
}