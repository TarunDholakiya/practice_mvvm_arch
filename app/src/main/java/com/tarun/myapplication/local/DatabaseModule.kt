package com.tarun.myapplication.local

import android.content.Context
import androidx.room.Room
import com.tarun.myapplication.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "shaadi-demo"
        ).build()
    }

    @Provides
    fun provideLogDao(database: AppDatabase): UserDao {
        return database.userDao()
    }
}