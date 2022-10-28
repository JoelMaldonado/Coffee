package com.jjmf.coffee.Ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.jjmf.coffee.Core.EstadosResult
import com.jjmf.coffee.Data.UseCase.CoffeeUseCase
import com.jjmf.coffee.Model.Coffee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeesViewModel @Inject constructor(
    private val ucCoffee: CoffeeUseCase
) : ViewModel(){

    fun getListLiveData() = liveData (Dispatchers.IO) {
        emit(EstadosResult.Cargando)
        try {
            emit(EstadosResult.Correcto(ucCoffee.getListLd()))
        }catch (ex:Exception){
            emit(EstadosResult.Error(ex.message.toString()))
        }
    }

    fun delete(coffe: Coffee) {
        viewModelScope.launch(Dispatchers.IO) {
            ucCoffee.delete(coffe)
        }
    }

}