package com.example.appfemmeit

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.net.URL

object PerfilService {

    //url do pythonanywhere
    val host =""
    val TAG = "API-FEMMEIT"

    fun getPerfil(context: Context):List<Perfil>{
        if(AndroidUtils.IsInternetOnline(context)) {
            val url = "$host/perfil"
            val json = HttpHelper.get(url)

            Log.d(TAG, json)

            return parserJson<List<Perfil>>(json)
        }else{
            return ArrayList()
        }
    }

    fun toJson(): String{
        return GsonBuilder().create().toJson(this)
    }

    fun save(perfil: Perfil): Response{
        val json = HttpHelper.post("$host/perfil", perfil.toJson())
        return parserJson<Response>(json)
    }

    fun delete(perfil: Perfil): Response {
        Log.d(TAG, perfil.id.toString())
        val url = "$host/perfil/${perfil.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)

    }

}