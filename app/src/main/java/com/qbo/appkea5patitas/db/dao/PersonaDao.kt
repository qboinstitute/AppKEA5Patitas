package com.qbo.appkea5patitas.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.qbo.appkea5patitas.db.entity.PersonaEntity

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(persona : PersonaEntity)

    @Update
    suspend fun actualizar(persona: PersonaEntity)

    @Query("DELETE FROM persona")
    suspend fun eliminartodo()

    @Query("SELECT * FROM persona LIMIT 1")
    fun obtener(): LiveData<PersonaEntity>

}