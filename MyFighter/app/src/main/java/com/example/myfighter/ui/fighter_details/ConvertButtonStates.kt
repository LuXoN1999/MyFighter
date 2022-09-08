package com.example.myfighter.ui.fighter_details

class ConvertButtonStates() {
    var weightIsInKg: Boolean = true
    var heightIsInCm: Boolean = true
    fun switchWeightUnit(){ weightIsInKg = !weightIsInKg }
    fun switchHeightUnit() { heightIsInCm = !heightIsInCm }
}