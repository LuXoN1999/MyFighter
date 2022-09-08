package com.example.myfighter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myfighter.model.Fighter

@Dao
interface FighterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFighter(fighter: Fighter)

    @Delete
    fun delete(fighter: Fighter)

    @Query("SELECT * FROM fighters WHERE Name = :name")
    fun getFighterByName(name: String): Fighter

    @Query("SELECT * FROM fighters WHERE Style = :fightingStyle")
    fun getFightersByFightingStyle(fightingStyle: String): LiveData<List<Fighter>>

    @Query("SELECT * FROM fighters WHERE Status = :isRetired")
    fun getFightersByRetirementStatus(isRetired: Boolean): LiveData<List<Fighter>>

    @Query("SELECT * FROM fighters WHERE Division = :division")
    fun getFightersByDivision(division: String): LiveData<List<Fighter>>

    @Query("SELECT * FROM fighters")
    fun getAllFighters(): LiveData<List<Fighter>>
}