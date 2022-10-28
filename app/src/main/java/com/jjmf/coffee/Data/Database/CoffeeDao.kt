package com.jjmf.coffee.Data.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jjmf.coffee.Model.Coffee

@Dao
interface CoffeeDao {

    @Insert
    suspend fun insert(coffee: Coffee)

    @Query("select * from tb_coffee")
    fun getListLD(): LiveData<List<Coffee>>

    @Delete
    fun delete(coffe: Coffee)

    @Update
    fun update(cafe: Coffee)
}