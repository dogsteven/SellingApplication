package com.dogsteven.sellingapplication.di

import com.dogsteven.sellingapplication.domain.service.authentication.AuthenticationService
import com.dogsteven.sellingapplication.domain.service.authentication.implementation.DummyAuthenticationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun bindDummyAuthenticationService(authenticationService: DummyAuthenticationService): AuthenticationService
}