package com.qbo.appkea5patitas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.qbo.appkea5patitas.apirest.request.RequestLogin
import com.qbo.appkea5patitas.apirest.request.RequestRegistro
import com.qbo.appkea5patitas.apirest.response.ResponseLogin
import com.qbo.appkea5patitas.apirest.response.ResponseRegistro
import com.qbo.appkea5patitas.repository.AuthRepository

class AuthViewModel : ViewModel() {

    var responseLogin : LiveData<ResponseLogin>
    var responseRegistro: LiveData<ResponseRegistro>
    private var repository = AuthRepository()
    init {
        responseLogin = repository.responseLogin
        responseRegistro = repository.responseRegistro
    }
    fun autenticarUsuario(usuario: String, password: String) {
        responseLogin = repository.autenticarUsuario(
            RequestLogin(usuario, password)
        )
    }
    fun registrarUsuario(nombre: String, apellidos: String, email: String,
    celular: String, usuario: String, password: String){
        responseRegistro = repository.registrarUsuario(
            RequestRegistro(apellidos, celular, email, nombre,
                password, usuario)
        )
    }



}