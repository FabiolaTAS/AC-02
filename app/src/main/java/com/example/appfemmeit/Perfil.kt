package com.example.appfemmeit

import com.google.gson.GsonBuilder
import java.io.Serializable

class Perfil: Serializable {

    var id: Long  = 0
    var nome = ""
    var email = ""
    var cpf = ""
    var dtNascimento =""
    var linkedin = ""
    var gitHub: String="";
    var login: String="";
    var senha: String="";
    var telefone1: String="";
    var telefone2: String="";
    var profissao: String="";
    var img: String="";
    var perfil: String="";



    override fun toString(): String{
        return  "Perfil(Nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}