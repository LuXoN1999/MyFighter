package com.example.myfighter.database

import androidx.lifecycle.LiveData
import com.example.myfighter.model.Fighter

interface FighterRepository {
    fun save(fighter: Fighter)
    fun delete(fighter: Fighter)
    fun getFighterByName(name: String): Fighter
    fun getAllFighters(): LiveData<List<Fighter>>
    fun getFightersByFightingStyle(fightingStyle: String): LiveData<List<Fighter>>
    fun getFightersByRetirementStatus(isRetired: Boolean): LiveData<List<Fighter>>
    fun getFightersByDivision(division: String): LiveData<List<Fighter>>
   /* fun getFightersByDivisionAndRetirementStatus(division: String, isRetired: Boolean): LiveData<List<Fighter>> */
}