package com.jjmf.coffee.Data.Module

import android.content.Context
import androidx.room.Room
import com.jjmf.coffee.Data.Database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseDatosModule {

    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "BDCoffee").build()
    }

}