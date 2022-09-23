package com.jjmf.coffee.Ui.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjmf.coffee.Data.AppDatabase
import com.jjmf.coffee.Model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrarViewModel : ViewModel() {

    fun insertarUsuario(user: Usuario, context: Context) {
        viewModelScope.launch(Dispatchers.IO){
            AppDatabase.getInstance(context).usuarioDao().insert(user)
        }
    }

}