package com.github.corentinc.httpcodescats.injection

import com.github.corentinc.httpcodescats.coroutines.DispatcherProvider
import com.github.corentinc.httpcodescats.coroutines.IDispatcherProvider
import com.github.corentinc.httpcodescats.repository.HttpCodesRepository
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
import com.github.corentinc.httpcodescats.usecase.HttpCodesUseCase
import com.github.corentinc.httpcodescats.usecase.IHttpCodesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ApplicationModule {
	@Binds
	@Singleton
	fun provideDispatcherProvider(dispatcherProvider: DispatcherProvider): IDispatcherProvider

	@Binds
	@Singleton
	fun provideHttpCodesRepository(httpCodesRepository: HttpCodesRepository): IHttpCodesRepository

	@Binds
	@Singleton
	fun provideHttpCodesUseCase(httpCodesUseCase: HttpCodesUseCase): IHttpCodesUseCase
}