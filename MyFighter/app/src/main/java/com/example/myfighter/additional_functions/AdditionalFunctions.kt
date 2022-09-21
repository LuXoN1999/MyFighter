package com.example.myfighter.additional_functions

import android.graphics.Color
import android.view.View
import android.webkit.URLUtil
import android.widget.RadioGroup
import android.widget.TextView
import com.example.myfighter.model.Fighter
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.math.pow


// ==================================================================== INPUT CHECK RELATED FUNCTIONS

fun checkIfImageURLIsValid(imageUrl: String?): Boolean{
    if(URLUtil.isValidUrl(imageUrl) && imageUrl != null) { return true }
    return false
}

fun inputsAreEmpty(name: String, nationality: String, age: String, weight: String, height: String): Boolean {
    if(name == "" || nationality == "" || age == "" ||  weight == "" || height == ""){
        return true
    }
    return false
}

fun atLeastOneButtonIsSelectedInRadiogroup(radioGroup: RadioGroup): Boolean{
    if(radioGroup.checkedRadioButtonId != -1){ return true }
    return false
}

// ==================================================================== DIVISION-RELATED FUNCTIONS

fun calculateDivision(weight: Double): String{
    if(weight > 52.5 && weight <= 56.7){
        return "Flyweight"
    }
    else if(weight > 56.7 && weight <= 61.2){
        return "Bantamweight"
    }
    else if(weight > 61.2 && weight <= 65.8){
        return "Featherweight"
    }
    else if(weight > 65.8 && weight <= 70.3){
        return "Lightweight"
    }
    else if(weight > 70.3 && weight <= 77.1){
        return "Welterweight"
    }
    else if(weight > 77.1 && weight <= 83.9){
        return "Middleweight"
    }
    else if(weight > 83.9 && weight <= 102.1){
        return "Light Heavyweight"
    }
    else if(weight > 102.1 && weight <= 120.2){
        return "Heavyweight"
    }
    else{
        return "Non-categorized"
    }
}

fun shortenDivisionName(divisionName: String): String{
    return when(divisionName){
        "Flyweight" -> "FLW"
        "Bantamweight" -> "BW"
        "Featherweight" -> "FW"
        "Lightweight" -> "LW"
        "Welterweight" -> "WW"
        "Middleweight" -> "MW"
        "Light Heavyweight" -> "LHW"
        "Heavyweight" -> "HW"
        else -> {
            "Non"
        }
    }
}

// ==================================================================== BMI-RELATED FUNCTIONS

fun calculateBMI(weight: Double, height: Double): Double{
    val decimalFormatInstance = createDecimalFormatInstance()
    return decimalFormatInstance.format(weight / (height / 100).pow(2)).toDouble()
}

fun returnBMIClassByBMI(bmi: Double):String{
    return if(bmi < 18.5) {
        "UNDERWEIGHT"
    }
    else if(bmi >= 18.5 && bmi < 24.9){
        "NORMAL"
    }
    else if(bmi >= 24.9 && bmi < 29.9){
        "OVERWEIGHT"
    }
    else if(bmi >= 29.9 && bmi < 34.9 ){
        "OBESE"
    }
    else {
        "EXTREMELY OBESE"
    }
}

fun returnColorByBMI(bmi: Double): Int {
    var bmiClass = returnBMIClassByBMI(bmi)
    when(bmiClass){
        "UNDERWEIGHT" -> return Color.parseColor("#4287F5")
        "NORMAL" -> return Color.GREEN
        "OVERWEIGHT" -> return Color.YELLOW
        "OBESE" -> return Color.parseColor("#F27C05")
        "EXTREMELY OBESE" -> return Color.RED
    }
    return Color.BLACK
}

// ==================================================================== UNIT CONVERSION RELATED FUNCTIONS
fun convertKgToLbs(weightInKg: Double): Double{
    val decimalFormatInstance = createDecimalFormatInstance()
    return decimalFormatInstance.format(weightInKg * 2.2).toDouble()
}

fun convertLbsToKg(weightInLbs: Double): Double{
    val decimalFormatInstance = createDecimalFormatInstance()
    return decimalFormatInstance.format(weightInLbs / 2.2).toDouble()
}

fun convertCmToFt(heightInCm: Double): Double{
    val decimalFormatInstance = createDecimalFormatInstance()
    return decimalFormatInstance.format(heightInCm / 30.48).toDouble()
}

fun convertFtToCm(heightInFt: Double): Double{
    val decimalFormatInstance = createDecimalFormatInstance()
    return decimalFormatInstance.format(heightInFt * 30.48).toDouble()
}

// ==================================================================== OTHER FUNCTIONS
val fightingStyleList: List<String> = Arrays.asList("Grappler","MMA","Kickboxer","Wrestler")
fun stringIsAFightingStyle(string: String): Boolean{
    return fightingStyleList.contains(string)

}

val divisionList: List<String> = Arrays.asList("Flyweight","Bantamweight","Featherweight"
    ,"Lightweight","Welterweight","Middleweight","Light Heavyweight", "Heavyweight")
fun stringIsADivision(string: String):Boolean{
    return divisionList.contains(string)
}

fun createDecimalFormatInstance(): DecimalFormat{
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df
}

fun checkListAndSetNoFighterTextVisibility(fighterList: List<Fighter>,noFightersText: TextView){
    if(fighterList.isNotEmpty()){
        noFightersText.visibility = View.INVISIBLE
    }
    else{
        noFightersText.visibility = View.VISIBLE
    }
}
