package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.corentinc.httpcodescats.coroutines.IDispatcherProvider
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.usecase.IHttpCodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HttpCodesListViewModel @Inject constructor(
	private val dispatcherProvider: IDispatcherProvider,
	private val httpCodesUseCase: IHttpCodesUseCase
) : ViewModel() {
	private val uiStateFlow = MutableStateFlow(UiState())
	val uiState: StateFlow<UiState> = uiStateFlow.asStateFlow()

	fun onStart() {
		viewModelScope.launch(dispatcherProvider.io()) {
			uiStateFlow.update {
				it.copy(
					httpCodes = httpCodesUseCase.getAllHttpCodes(),
					isLoading = false
				)
			}
		}
	}

	fun onFilterChanged(newValue: String) {
		viewModelScope.launch(dispatcherProvider.io()) {
			uiStateFlow.update {
				it.copy(
					httpCodes = httpCodesUseCase.filterHttpCodes(newValue),
					isLoading = false,
					filter = newValue
				)
			}
		}
	}

	data class UiState(
		var httpCodes: List<HttpCode> = emptyList(),
		var isLoading: Boolean = true,
		var filter: String = ""
	)
}