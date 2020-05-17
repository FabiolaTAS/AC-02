package com.example.appfemmeit

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PerfilDAO {

    @Query("SELECT * FROM perfil where id = :id")
    fun getById(id: Long): Perfil?

    @Query("SELECT * FROM perfil")
    fun findAll(): List<Perfil>

    @Insert
    fun insert(perfil: Perfil)

    @Delete
    fun delete(perfil: Perfil)
}