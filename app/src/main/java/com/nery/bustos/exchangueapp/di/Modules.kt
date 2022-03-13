package com.nery.bustos.exchangueapp.di

import com.nery.bustos.exchangueapp.data.DataSource
import com.nery.bustos.exchangueapp.data.DataSourceImpl
import com.nery.bustos.exchangueapp.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SplashUseCaseModule {
    @Provides
    fun splashUseCaseProvider(): SplashUseCase = SplashUseCaseImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class ConverterUseCaseModule {
    @Provides
    fun converterUseCaseProvider(): ConverterUseCase = ConverterUseCaseImpl()
}


@Module
@InstallIn(SingletonComponent::class)
class ListUseCaseModule {
    @Provides
    fun listUseCaseProvider(): ListUseCase = ListUseCaseImpl()
}

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun dataSourceProvider(): DataSource = DataSourceImpl()
}