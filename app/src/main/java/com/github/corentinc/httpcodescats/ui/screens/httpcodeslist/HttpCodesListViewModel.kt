package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.lifecycle.ViewModel
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HttpCodesListViewModel @Inject constructor(
	httpCodesRepository: IHttpCodesRepository
) : ViewModel() {
	val httpCodes = httpCodesRepository.httpCodes
}