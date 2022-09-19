package com.example.myfighter.ui.fighter_list

import androidx.lifecycle.ViewModel
import com.example.myfighter.additional_functions.stringIsADivision
import com.example.myfighter.additional_functions.stringIsAFightingStyle
import com.example.myfighter.database.FighterRepository

class FighterListViewModel(private val fighterRepository: FighterRepository) : ViewModel() {
    var fighters = fighterRepository.getAllFighters()

    fun filterFightersByDivision(division: String){
        fighters = if(stringIsADivision(division)) {
            fighterRepository.getFightersByDivision(division)
        } else{
            fighterRepository.getAllFighters()
        }
    }

    fun filterFightersByStatus(retirementStatus: String) {
        when(retirementStatus){
            "Retired" -> fighters = fighterRepository.getFightersByRetirementStatus(true)
            "Active" -> fighters = fighterRepository.getFightersByRetirementStatus(false)
            "All" -> fighters = fighterRepository.getAllFighters()
        }
    }

    fun filterFightersByFightingStyle(fightingStyle: String){
        fighters = if(stringIsAFightingStyle(fightingStyle)){
            fighterRepository.getFightersByFightingStyle(fightingStyle)
        }
        else {
            fighterRepository.getAllFighters()
        }
    }

}