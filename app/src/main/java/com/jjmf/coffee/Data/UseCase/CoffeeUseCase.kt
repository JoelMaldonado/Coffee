package com.jjmf.coffee.Data.UseCase

import androidx.lifecycle.LiveData
import com.jjmf.coffee.Data.Repository.CoffeeRepository
import com.jjmf.coffee.Model.Coffee
import javax.inject.Inject

class CoffeeUseCase @Inject constructor(
    private val rep: CoffeeRepository
){
    suspend fun insertar(coffee: Coffee):String{
        return try {
            rep.insert(coffee)
            "Insertado"
        }catch (ex:Exception){
            ex.message.toString()
        }
    }
    suspend fun getListLd():LiveData<List<Coffee>>{
        return rep.getListLD()
    }

    suspend fun delete(coffe: Coffee) {
        rep.delete(coffe)
    }

    suspend fun update(cafe: Coffee) {
        rep.update(cafe)
    }
}