package com.example.appfemmeit

import android.content.Context

object PerfilService {

    fun getPerfil(context: Context):List<Perfil>{
        val perfil = mutableListOf<Perfil>()
        for (i in 1..10){
            val p = Perfil();
            p.nome = "Usuario $i"
            p.cpf = "cpf $i"
            p.email = "email $i"
            p.dtNascimento = "dtNascimento $i"
            p.linkedin = "linkedin $i"
            p.login = "login $i"
            p.telefone1 = "telefone1 $i"
            p.profissao = "profissao $i"
            p.img = "https://nailtime.com.br/wp-content/uploads/2018/11/00-Empoderamento-Feminino.jpg"
            p.perfil = "perfil $i"
            perfil.add(p)
        }

        return  perfil
    }
}