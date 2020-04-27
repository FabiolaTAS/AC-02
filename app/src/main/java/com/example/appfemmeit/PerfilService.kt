package com.example.appfemmeit

import android.content.Context

object PerfilService {

    fun getPerfil(context: Context):List<Perfil>{
        val perfil = mutableListOf<Perfil>()
        for (i in 1..10){
            val p = Perfil();
            p.nome = "Usuario $i"
            p.cpf = "cpf $i"
            p.emaail = "email $i"
            p.dtNascimento = "drNascimento $i"
            p.linkedin = "limkedin $i"
            p.login = "login $i"
            p.telefone1 = "telefone1 $i"
            p.profissao = "profissao $i"
            p.img = "https://img1.gratispng.com/20180326/cze/kisspng-women-s-empowerment-woman-international-women-s-da-women-day-5ab8d21502e510.9037538015220618450119.jpg"

            perfil.add(p)
        }

        return  perfil
    }
}