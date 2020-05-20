package com.example.appfemmeit

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "perfil")
class Perfil : Serializable {

    var cpf = ""
    var dtNascimento = ""
    var email = ""
    var gitHub = ""
    @PrimaryKey
    var id: Long = 0
    var img = ""
    var linkedin = ""
    var login = ""
    var nome = ""
    var perfil = ""
    var profissao = ""
    var senha = ""
    var telefone1 = ""
    var telefone2 = ""


    override fun toString(): String {
        return "Usuario (Nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}