package com.example.appfemmeit

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Perfil::class), version = 1)
abstract class PERFILDatabase : RoomDatabase() {
    abstract fun perfilDAO  (): PerfilDAO

}