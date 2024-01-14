package com.jrektabasa.androidmvvm.di

import com.jrektabasa.androidmvvm.repository.BlogRepository
import com.jrektabasa.androidmvvm.repository.impl.BlogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsBlogRepository(
        blogRepositoryImpl: BlogRepositoryImpl
    ): BlogRepository
}