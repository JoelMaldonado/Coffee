package com.jjmf.coffee.Ui.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.coffee.Data.AppDatabase
import com.jjmf.coffee.Model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {

    private val _usuario : MutableLiveData<Int> = MutableLiveData()
    val usuario : LiveData<Int> = _usuario

    fun login(usuario: String, clave: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            val user = AppDatabase.getInstance(context).usuarioDao().login(usuario, clave)
            withContext(Dispatchers.Main){
                _usuario.value = user
            }
        }
    }
}