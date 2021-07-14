package com.qbo.appkea5patitas.repository

import androidx.lifecycle.LiveData
import com.qbo.appkea5patitas.db.dao.PersonaDao
import com.qbo.appkea5patitas.db.entity.PersonaEntity

class PersonaRepository(private val personaDao: PersonaDao) {
    suspend fun insertar(personaEntity: PersonaEntity){
        personaDao.insertar(personaEntity)
    }
    suspend fun actualizar(personaEntity: PersonaEntity){
        personaDao.actualizar(personaEntity)
    }
    suspend fun eliminartodo(){
        personaDao.eliminartodo()
    }
    fun obtener(): LiveData<PersonaEntity>{
        return personaDao.obtener()
    }

}