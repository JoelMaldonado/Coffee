package com.jjmf.coffee.Ui.ViewModel

import androidx.lifecycle.*
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Data.UseCase.UsuarioUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val ucUsuario: UsuarioUseCase
) : ViewModel() {

    fun loginNuevo(usuario: String, clave: String) = liveData(Dispatchers.IO) {
        emit(EstadosResult.Cargando)
        try {
            emit(ucUsuario.login(usuario, clave))
        } catch (ex: Exception) {
            emit(EstadosResult.Error(ex.message.toString()))
        }
    }
}