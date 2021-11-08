package com.rasenyer.usersapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rasenyer.usersapp.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(context, UserDatabase::class.java, "UserDatabase").createFromAsset("database/UserDatabase.db").build()
                INSTANCE = instance
                instance

            }

        }

    }

}
