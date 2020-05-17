package com.example.appfemmeit

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "perfil")
class Perfil : Serializable {

    var email = ""
    var img = ""
    @PrimaryKey
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