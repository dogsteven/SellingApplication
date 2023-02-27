package com.dogsteven.sellingapplication.di

import android.content.Context
import androidx.room.Room
import com.dogsteven.sellingapplication.domain.dao.OrderDAO
import com.dogsteven.sellingapplication.domain.database.SellingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideSellingDatabase(
        @ApplicationContext context: Context
    ): SellingDatabase = Room.databaseBuilder(context = context, SellingDatabase::class.java, name = "selling_database").build()

    @Singleton
    @Provides
    fun provideOrderDAO(
        database: SellingDatabase
    ): OrderDAO = database.getOrderDAO()
}