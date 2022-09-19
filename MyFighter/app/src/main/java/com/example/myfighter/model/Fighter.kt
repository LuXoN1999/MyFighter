package com.example.myfighter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fighters")
data class Fighter(
    @PrimaryKey
    @ColumnInfo(name="Name") val name: String,
    @ColumnInfo(name="Nickname") val nickname: String?,
    @ColumnInfo(name="Nationality") val nationality: String,
    @ColumnInfo(name="Age") var age: Int,
    @ColumnInfo(name="Retired") var isRetired: Boolean,
    @ColumnInfo(name="Weight") var weightKg: Double,
    @ColumnInfo(name="Division") var division: String,
    @ColumnInfo(name="Height") var heightCm: Double,
    @ColumnInfo(name="Style") var fightingStyle: String,
    @ColumnInfo(name="ImageURL") var imageURL: String?
    )
