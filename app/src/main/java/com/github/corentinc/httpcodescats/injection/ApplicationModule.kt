package com.github.corentinc.httpcodescats.injection

import com.github.corentinc.httpcodescats.repository.HttpCodesRepository
import com.github.corentinc.httpcodescats.repository.IHttpCodesRepository
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
	fun provideHttpCodesRepository(httpCodesRepository: HttpCodesRepository): IHttpCodesRepository
}