package com.moataz.better_human.better_life.injection.provide

import android.content.Context
import com.moataz.better_human.better_life.data.local.database.MoharebDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        MoharebDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideHabitDao(database: MoharebDatabase) = database.habitDao()
}