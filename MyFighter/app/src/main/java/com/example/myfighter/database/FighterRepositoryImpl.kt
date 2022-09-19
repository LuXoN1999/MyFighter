package com.example.myfighter.database

import androidx.lifecycle.LiveData
import com.example.myfighter.model.Fighter

class FighterRepositoryImpl(val fighterDAO: FighterDAO): FighterRepository {
    override fun saveFighter(fighter: Fighter) = fighterDAO.insertFighter(fighter)
    override fun delete(fighter: Fighter) = fighterDAO.delete(fighter)
    override fun getFighterByName(name: String): Fighter = fighterDAO.getFighterByName(name)
    override fun getAllFighters(): LiveData<List<Fighter>> = fighterDAO.getAllFighters()
    override fun getFightersByFightingStyle(fightingStyle: String): LiveData<List<Fighter>> =
        fighterDAO.getFightersByFightingStyle(fightingStyle)

    override fun getFightersByRetirementStatus(isRetired: Boolean): LiveData<List<Fighter>> =
        fighterDAO.getFightersByRetirementStatus(isRetired)

    override fun getFightersByDivision(division: String): LiveData<List<Fighter>> = fighterDAO.getFightersByDivision(division)
}