package com.example.myfighter.ui.fighter_details

import androidx.lifecycle.ViewModel
import com.example.myfighter.model.Fighter
import com.example.myfighter.database.FighterRepository

class FighterDetailsViewModel(private val fighterRepository: FighterRepository) : ViewModel() {
    fun getFighterByName(name: String?): Fighter? {
        var fighter: Fighter? = null
        name?.let { fighter = fighterRepository.getFighterByName(name) }
        return fighter
    }

    fun delete(fighter: Fighter){
        fighterRepository.delete(fighter)
    }
}