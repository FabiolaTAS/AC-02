package com.example.appfemmeit

import java.io.Serializable

class Perfil: Serializable {

    var id: Long  = 0;
    var nome: String = "";
    var email: String ="";
    var cpf: String ="";
    var dtNascimento: String="";
    var linkedin: String="";
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
}