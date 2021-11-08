package com.rasenyer.usersapp.db

import androidx.room.Dao
import androidx.room.Query
import com.rasenyer.usersapp.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user_table WHERE username = :username ORDER BY username ASC")
    fun getByUsername(username: String): Flow<List<User>>

}
