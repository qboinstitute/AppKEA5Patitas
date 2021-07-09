package com.qbo.appkea5patitas.repository

import androidx.lifecycle.MutableLiveData
import com.qbo.appkea5patitas.apirest.PatitasCliente
import com.qbo.appkea5patitas.apirest.request.RequestLogin
import com.qbo.appkea5patitas.apirest.request.RequestRegistro
import com.qbo.appkea5patitas.apirest.response.ResponseLogin
import com.qbo.appkea5patitas.apirest.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var responseLogin = MutableLiveData<ResponseLogin>()
    var responseRegistro = MutableLiveData<ResponseRegistro>()

    fun autenticarUsuario(requestLogin: RequestLogin)
    : MutableLiveData<ResponseLogin>{
        val call : Call<ResponseLogin> = PatitasCliente
            .retrofitService.login(requestLogin)
        call.enqueue(object: Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                responseLogin.value = response.body()
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {

            }
        })
        return responseLogin
    }
    fun registrarUsuario(requestRegistro: RequestRegistro)
    : MutableLiveData<ResponseRegistro>
    {
        val call : Call<ResponseRegistro> =
            PatitasCliente.retrofitService.registro(requestRegistro)
        call.enqueue(object: Callback<ResponseRegistro>{
            override fun onResponse(
                call: Call<ResponseRegistro>,
                response: Response<ResponseRegistro>
            ) {
                responseRegistro.value = response.body()
            }
            override fun onFailure(call: Call<ResponseRegistro>, t: Throwable) {

            }
        })
        return responseRegistro
    }


}