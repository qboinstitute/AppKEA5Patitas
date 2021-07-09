package com.qbo.appkea5patitas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.qbo.appkea5patitas.apirest.response.ResponseMascota
import com.qbo.appkea5patitas.apirest.response.ResponseRegistro
import com.qbo.appkea5patitas.repository.MascotaRepository

class MascotaViewModel : ViewModel() {
    private var repository = MascotaRepository()
    var responseRegistro: LiveData<ResponseRegistro> = repository.responseRegistro

    fun listarMascotas(): LiveData<List<ResponseMascota>>{
        return repository.listarMascotas()
    }

    fun registrarVoluntario(idPersona: Int){
        responseRegistro = repository.registrarVoluntario(idPersona)
    }

}