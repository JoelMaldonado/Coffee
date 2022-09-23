package com.jjmf.coffee.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jjmf.coffee.Model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    companion object{
        var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "DBCoffe").build()
            }
            return instance as AppDatabase
        }
    }
}