package com.jjmf.coffee.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tb_coffee")
data class Coffee(
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "preparacion") var preparacion:String,
    @ColumnInfo(name = "foto") val foto:String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id:Int = 0
) : Serializable
