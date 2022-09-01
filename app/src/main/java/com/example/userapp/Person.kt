package com.example.userapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "personTable")
data class Person(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val fName: String,
    val lName: String,
    val phone: String,
    @NotNull
    val email: String,
    val city: String
)
