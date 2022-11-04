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
class DetalleViewModel @Inject constructor(
    private val cafeUc : CoffeeUseCase
) : ViewModel(){
    fun actualizar(cafe: Coffee) = liveData(Dispatchers.IO){
        emit(EstadosResult.Cargando)
        try {
            emit(cafeUc.update(cafe))
        }catch (e:Exception){
            emit(EstadosResult.Error(e.message.toString()))
        }
    }

    fun delete(coffe: Coffee) = liveData(Dispatchers.IO){
        emit(EstadosResult.Cargando)
        kotlinx.coroutines.delay(1000)
        try {
            emit(cafeUc.delete(coffe))
        }catch (e:Exception){
            emit(EstadosResult.Error(e.message.toString()))
        }
    }

}