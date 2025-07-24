package com.example.horseracingapp.di

import android.content.Context
import androidx.room.Room
import com.example.horseracingapp.data.local.AppDatabase
import com.example.horseracingapp.data.local.RaceDao
import com.example.horseracingapp.data.repository.RaceRepositoryImpl
import com.example.horseracingapp.data.repository.RaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "horse_race_db"
        ).build()

    @Provides
    fun provideRaceDao(db: AppDatabase): RaceDao = db.raceDao()

    @Provides
    @Singleton
    fun provideRaceRepository(raceDao: RaceDao): RaceRepository =
        RaceRepositoryImpl(raceDao)
}
