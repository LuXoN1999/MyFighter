package com.example.myfighter.ui.new_fighter

import androidx.lifecycle.ViewModel
import com.example.myfighter.database.FighterRepository
import com.example.myfighter.model.Fighter

class NewFighterViewModel(
    private val fighterRepository: FighterRepository
    ) : ViewModel() {
    fun save(name: String, nickname: String?, nationality: String, age: Int, isRetired: Boolean, weight: Double,division: String,height: Double,
    style: String, imageUrl: String?) {
        fighterRepository.saveFighter(Fighter(name,nickname,nationality,age,isRetired,weight,division,height,style,imageUrl))
    }
}