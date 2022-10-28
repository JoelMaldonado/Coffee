package com.jjmf.coffee.Data.Repository

import androidx.lifecycle.LiveData
import com.jjmf.coffee.Data.Database.AppDatabase
import com.jjmf.coffee.Model.Coffee
import javax.inject.Inject

interface CoffeeRepository {
    suspend fun insert(coffee: Coffee)
    suspend fun getListLD(): LiveData<List<Coffee>>
    suspend fun delete(coffe: Coffee)
    suspend fun update(cafe: Coffee)
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

    override suspend fun delete(coffe: Coffee) {
        db.coffeeDao().delete(coffe)
    }

    override suspend fun update(cafe: Coffee) {
        db.coffeeDao().update(cafe)
    }


}