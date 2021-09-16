package com.group21.android.basketballcounter

import android.app.Application

class GameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GameRepository.initialize(this)
    }
}