package com.example.appfemmeit

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: PERFILDatabase

    init {
        val appContext = PERFILApplication. getInstance().applicationContext
        dbInstance = Room.databaseBuilder(

            appContext,
            PERFILDatabase::class.java,
            "perfil.sqlite"
        ).build()
    }


    fun getPerfilDAO(): PerfilDAO {
        return dbInstance.perfilDAO()
    }


}