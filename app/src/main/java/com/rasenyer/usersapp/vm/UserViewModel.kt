package com.rasenyer.usersapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rasenyer.usersapp.model.User
import com.rasenyer.usersapp.db.UserDao
import kotlinx.coroutines.flow.Flow

class UserViewModel(private val userDao: UserDao): ViewModel() {

    fun getAll(): Flow<List<User>> = userDao.getAll()

    fun getByUsername(username: String): Flow<List<User>> = userDao.getByUsername(username)

}

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }

}
