package com.example.appfemmeit

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.logincontraint.*


class MainActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logincontraint)
        logo_App.setImageResource(R.drawable.femmeit)
        mensagem_login.setText(R.string.str_login)
        user.setText(R.string.user)
        password.setText(R.string.password)
        button_login.setText(R.string.button_login)

        fun onClickLogin(){
            val valorUser = label_user.text.toString()
            val valorPass = label_password.text.toString()

            val parans = Bundle()
            parans.putString("nome",valorUser)

            var intent = Intent(this , TelaInicialActivity::class.java)
            intent.putExtras(parans)

            //incluir validacao de login
            if (valorUser != "aluno" && valorPass != "impacta"){
                androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Senha ou Login invalidos!")
                .setPositiveButton("Tentar Novamente") {
                        dialog, which ->
                        dialog.dismiss()}.create().show()
            }else{
                startActivity(intent)
            }
        }

        //click do botão e logica para login
        button_login.setOnClickListener{
            onClickLogin()
        }
    }
}

