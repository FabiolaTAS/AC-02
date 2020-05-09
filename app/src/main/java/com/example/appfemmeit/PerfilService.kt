package com.example.appfemmeit

import android.content.Context
import android.util.Log
import java.net.URL

object PerfilService {

    //url do pythonanywhere
    val host =""
    val TAG = "API-FEMMEIT"

    fun getPerfil(context: Context):List<Perfil>{

        val url = "$host/perfil"
        val json = URL(url).readText()

        Log.d(TAG, json)

        return ArrayList()

    }
}