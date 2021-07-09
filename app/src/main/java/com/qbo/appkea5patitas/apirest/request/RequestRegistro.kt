package com.qbo.appkea5patitas.apirest.request

data class RequestRegistro(
    val apellidos: String,
    val celular: String,
    val email: String,
    val nombres: String,
    val password: String,
    val usuario: String
)