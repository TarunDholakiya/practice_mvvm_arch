package com.tarun.myapplication

import android.app.Application
import androidx.room.Room
import com.tarun.myapplication.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

class MyApplication : Application() {
    companion object {
        private lateinit var instance: MyApplication

        @JvmStatic
        fun get() = instance

        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "shaadi-demo"
        ).allowMainThreadQueries()
            .build()
    }
}