package com.jjmf.coffee.App

import android.app.Application
import com.jjmf.coffee.Core.Preferencias
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class BaseApp : Application(){
    companion object {
        lateinit var prefs: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferencias(applicationContext)

    }
}