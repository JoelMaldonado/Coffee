package com.jjmf.coffee.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jjmf.coffee.Model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
}