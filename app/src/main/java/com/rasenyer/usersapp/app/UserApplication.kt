package com.rasenyer.usersapp.app

import android.app.Application
import com.rasenyer.usersapp.db.UserDatabase

class UserApplication : Application() {
    val database: UserDatabase by lazy { UserDatabase.getDatabase(this) }
}
