package com.jjmf.coffee.Data.UseCase

import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Data.Repository.UsuarioRepository
import com.jjmf.coffee.Model.Usuario
import javax.inject.Inject

class UsuarioUseCase @Inject constructor(
    private val repository : UsuarioRepository
) {

    suspend fun login(usuario:String, clave:String) : EstadosResult<Usuario>{
        val listaUsuarios = repository.getList()
        val bool = usuario+clave in listaUsuarios.map { it.usuario+it.clave }
        return if (bool){
            val usuario = listaUsuarios[listaUsuarios.indexOfFirst { it.usuario+it.clave == usuario+clave }]
            EstadosResult.Correcto(usuario)
        }else{
            EstadosResult.Error("EL usuario y/o contraseña no son validos")
        }
    }

    suspend fun insert(user: Usuario): EstadosResult<String> {
        return try {
            repository.insertUsuario(user)
            EstadosResult.Correcto("Usuario insertado")
        }catch (ex:Exception){
            EstadosResult.Error(ex.message.toString())
        }
    }

}