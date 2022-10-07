package com.jjmf.coffee.Ui.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Data.AppDatabase
import com.jjmf.coffee.Data.Repository.UsuarioRepository
import com.jjmf.coffee.Data.UseCase.UsuarioUseCase
import com.jjmf.coffee.Model.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrarViewModel @Inject constructor(
    private val ucUsuario : UsuarioUseCase
): ViewModel() {

    fun insertarUsuario(user: Usuario)  = liveData(Dispatchers.IO){
        emit(EstadosResult.Cargando)
        try {
            emit(ucUsuario.insert(user))
        }catch (ex:Exception){
            emit(EstadosResult.Error(ex.message.toString()))
        }
    }

}