package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

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
class HttpCodesListViewModel @Inject constructor(
	private val httpCodesRepository: IHttpCodesRepository
) : ViewModel() {
	private val uiStateFlow = MutableStateFlow(UiState())
	val uiState: StateFlow<UiState> = uiStateFlow.asStateFlow()

	fun onStart() {
		uiStateFlow.update {
			it.copy(
				httpCodes = httpCodesRepository.getAllHttpCodes(),
				isLoading = false
			)
		}
	}

	data class UiState(
		var httpCodes: List<HttpCode> = emptyList(),
		var isLoading: Boolean = true
	)
}