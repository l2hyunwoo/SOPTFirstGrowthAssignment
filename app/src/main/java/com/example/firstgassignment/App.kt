package com.example.firstgassignment

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
    }
}