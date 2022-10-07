package com.jjmf.coffee.Data.Repository

import com.jjmf.coffee.Data.AppDatabase
import com.jjmf.coffee.Model.Usuario
import javax.inject.Inject

interface UsuarioRepository {

    suspend fun getList():List<Usuario>

    suspend fun insertUsuario(usuario: Usuario)

    suspend fun verificarSiExiste(usuario: String) : Boolean
}

class UsuarioRepositoryImpl @Inject constructor(
    private val db:AppDatabase
) : UsuarioRepository{

    override suspend fun getList(): List<Usuario> {
        return db.usuarioDao().getList()
    }

    override suspend fun insertUsuario(usuario: Usuario) {
        db.usuarioDao().insert(usuario)
    }

    override suspend fun verificarSiExiste(usuario: String): Boolean {
        return usuario in getList().map { it.usuario }
    }

}