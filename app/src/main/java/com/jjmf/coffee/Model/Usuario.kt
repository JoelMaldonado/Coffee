package com.jjmf.coffee.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_usuario")
data class Usuario(
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "apellido") val apellido:String,
    @ColumnInfo(name = "fechaNac") val fechaNac:String,
    @ColumnInfo(name = "genero") val genero:String,
    @ColumnInfo(name = "usuario") val usuario:String,
    @ColumnInfo(name = "clave") val clave:String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int = 0
)
