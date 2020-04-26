package com.example.appfemmeit

class Perfil {

    var id: Long  = 0;
    var nome: String = "";
    var emaail: String ="";
    var cpf: String ="";
    var dtNascimento: String="";
    var linkedin: String="";
    var gitHub: String="";
    var login: String="";
    var senha: String="";
    var telefone1: String="";
    var telefone2: String="";
    var profissao: String="";
    var img: String="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSWOVbLqMK3VoUfjO7YegNGQMmdWIJBSP5Xe28PtIhp8m3pK9mn&usqp=CAU";

    override fun toString(): String{
        return  "Perfil(nome='$nome')"
        return  "Perfil(nome='$profissao')"
    }
}