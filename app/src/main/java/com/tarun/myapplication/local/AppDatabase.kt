package com.tarun.myapplication.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tarun.myapplication.model.User
import com.tarun.myapplication.dao.UserDao

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}