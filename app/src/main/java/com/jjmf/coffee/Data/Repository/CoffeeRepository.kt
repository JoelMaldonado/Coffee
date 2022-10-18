package com.jjmf.coffee.Data.Repository

import androidx.lifecycle.LiveData
import com.jjmf.coffee.Data.Database.AppDatabase
import com.jjmf.coffee.Model.Coffee
import javax.inject.Inject

interface CoffeeRepository {
    suspend fun insert(coffee: Coffee)
    suspend fun getListLD(): LiveData<List<Coffee>>
}

class CoffeeRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : CoffeeRepository{
    override suspend fun insert(coffee: Coffee) {
        db.coffeeDao().insert(coffee)
    }

    override suspend fun getListLD(): LiveData<List<Coffee>> {
        return db.coffeeDao().getListLD()
    }


}