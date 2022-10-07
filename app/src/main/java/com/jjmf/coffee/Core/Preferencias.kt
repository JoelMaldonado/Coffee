package com.jjmf.coffee.Core

import android.content.Context

class Preferencias(val context: Context) {

    private val SHARED_NAME = "MYSHAREDTB"

    private val KEY_LENGUAJE = "KEY_LENGUAJE"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveLenguaje(lenguaje: String) {
        storage.edit().putString(KEY_LENGUAJE, lenguaje).apply()
    }

    fun getLenguaje(): String = storage.getString(KEY_LENGUAJE, "es") ?: ""
}