package com.example.quiz3.data.local.db

import androidx.room.*
import com.example.quiz3.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(vararg users: User)
}