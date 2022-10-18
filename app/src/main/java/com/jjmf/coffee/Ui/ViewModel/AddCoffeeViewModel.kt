package com.jjmf.coffee.Ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Data.UseCase.CoffeeUseCase
import com.jjmf.coffee.Model.Coffee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
@HiltViewModel
class AddCoffeeViewModel @Inject constructor(
    private val ucCoffee: CoffeeUseCase
) : ViewModel(){
    fun insert(coffee: Coffee) = liveData(Dispatchers.IO) {
        emit(EstadosResult.Cargando)
        try {
            emit(EstadosResult.Correcto( ucCoffee.insertar(coffee)))
        }catch (ex:Exception){
            emit(EstadosResult.Error(ex.message.toString()))
        }
    }

}