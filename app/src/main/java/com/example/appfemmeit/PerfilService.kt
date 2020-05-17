package com.example.appfemmeit

import android.content.Context
import android.content.LocusId
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object PerfilService {

    //url do pythonanywhere
    val host = "https://fassuncao.pythonanywhere.com"
    val TAG = "API-FEMMEIT"


    fun getPerfil(context: Context, id: Long): Perfil? {
        if (AndroidUtils.IsInternetOnline()) {
            val url = "$host/perfils/${id}"
            val json = HttpHelper.get(url)
            val disciplina = parserJson<Perfil>(json)

            return disciplina
        } else {
            val dao = DatabaseManager.getPerfilDAO()
            val disciplina = dao.getById(id)
            return disciplina
        }

    }

    fun saveOffiline(perfil: Perfil): Boolean {
        val dao = DatabaseManager.getPerfilDAO()

        if (!existePerfil(perfil)) {
            dao.insert(perfil)
        }
        return true
    }


    fun existePerfil(perfil: Perfil): Boolean {
        val dao = DatabaseManager.getPerfilDAO()
        return dao.getById(perfil.id) != null
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

    fun save(perfil: Perfil): Response {
        if (AndroidUtils.IsInternetOnline()) {
            val json = HttpHelper.post("$host/perfils", perfil.toJson())
            return parserJson(json)
        } else {
            saveOffiline(perfil)
            return Response("OK", "Cadastro sucesso")
        }
    }

    fun delete(disciplina: Perfil): Response {
        if (AndroidUtils.IsInternetOnline()) {
            val url = "$host/perfils/${disciplina.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getPerfilDAO()
            dao.delete(disciplina)
            return Response("OK",  "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>() {}.type
        return Gson().fromJson<T>(json, type)

    }

}