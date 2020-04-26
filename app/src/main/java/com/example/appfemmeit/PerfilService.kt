package com.example.appfemmeit

import android.content.Context

object PerfilService {

    fun getPerfil(context: Context):List<Perfil>{
        val perfil = mutableListOf<Perfil>()
        for (i in 1..10){
            val p = Perfil();
            p.nome = "nome $i"
            p.cpf = "cpf $i"
            p.emaail = "email $i"
            p.dtNascimento = "drNascimento $i"
            p.linkedin = "limkedin $i"
            p.login = "login $i"
            p.telefone1 = "telefone1 $i"
            p.profissao = "profissao $i"

            perfil.add(p)
        }

        return  perfil
    }
}