package com.jjmf.coffee.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jjmf.coffee.Model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("select * from tb_usuario")
    suspend fun getList(): List<Usuario>

    @Query("select count(*) from tb_usuario where usuario=:user and clave=:cla")
    suspend fun login(user: String, cla: String) : Int

}