package com.jjmf.coffee.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jjmf.coffee.Model.Coffee
import com.jjmf.coffee.Model.Usuario

@Database(entities = [Usuario::class, Coffee::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun coffeeDao(): CoffeeDao
}