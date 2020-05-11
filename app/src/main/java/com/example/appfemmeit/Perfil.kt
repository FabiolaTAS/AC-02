package com.example.appfemmeit

import com.google.gson.GsonBuilder
import java.io.Serializable

class Perfil : Serializable {

    var email = ""
    var img = ""
    var id: Long = 0
    var linkedin = ""
    var nome = ""
    var telefone1 = ""
    var perfil = ""
    var cpf = ""
    var dtNascimento = ""
    var gitHubg = ""
    var login = ""
    var senha = ""
    var telefone2 = ""
    var profissao = ""

    override fun toString(): String {
        return "Usuario (Nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}