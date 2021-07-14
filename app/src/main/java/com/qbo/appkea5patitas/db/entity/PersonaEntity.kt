package com.qbo.appkea5patitas.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persona")
data class PersonaEntity (
    @PrimaryKey
    val id: Int,
    val apellidos: String,
    val celular: String,
    val email: String,
    val esvoluntario: String,
    val nombres: String,
    val password: String,
    val usuario: String
        )