package com.qbo.appkea5patitas.apirest.response

data class ResponseLogin(
    val apellidos: String,
    val celular: String,
    val email: String,
    val esvoluntario: String,
    val idpersona: String,
    val mensaje: String,
    val nombres: String,
    val password: String,
    val rpta: Boolean,
    val usuario: String
)