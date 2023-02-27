package com.dogsteven.sellingapplication.di

import com.dogsteven.sellingapplication.domain.repository.remote.user.UserRepository
import com.dogsteven.sellingapplication.domain.repository.remote.user.implementation.DummyUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDummyUserRepository(userRepository: DummyUserRepository): UserRepository
}