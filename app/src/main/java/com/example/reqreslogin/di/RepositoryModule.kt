package com.example.reqreslogin.di

import com.example.reqreslogin.data.api.ReqResApi
import com.example.reqreslogin.data.repository.ReqResRepository
import com.example.reqreslogin.data.repository.ReqResRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(reqResApi: ReqResApi): ReqResRepository {
        return ReqResRepositoryImpl(reqResApi)
    }
}