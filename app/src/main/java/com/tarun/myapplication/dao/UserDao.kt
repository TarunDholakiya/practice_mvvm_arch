package com.tarun.myapplication.dao

import androidx.room.*
import com.tarun.myapplication.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("DELETE FROM user")
    fun deleteAll()

    @Update
    fun updateUser(user: User)
}