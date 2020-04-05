package com.example.appfemmeit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)


        //setando valores enviados
        var args = intent.extras
        val nome = args?.getString("nome")

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tela Incial"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Toast.makeText(this, "Bem Vindo $nome", Toast.LENGTH_LONG).show()
        textoInicial.setText("Seja Bem vindo " + nome)

        bt_cadastrarMulher.setText(R.string.mulheres)
        bt_cadastrarEmpresa.setText(R.string.empresa)
        bt_cadastrarComunidades.setText(R.string.comunidade)
        button_sair.setText(R.string.bt_sair)


        //click do botão para sair
        button_sair.setOnClickListener{
            clickSair()
        }

        //click do botão para cadastro Mulheres
        bt_cadastrarMulher.setOnClickListener{
            clickMulheres()
        }

        bt_cadastrarEmpresa.setOnClickListener{
            clickEmpresas()
        }

        bt_cadastrarComunidades.setOnClickListener{
            clickComunidade()
        }

    }

    //funcao Botão comunidade para tela cadastro
    fun clickComunidade(){
        var intent = Intent(this , CadastroGeralActivity::class.java)
        startActivity(intent)

    val mulher = "Mulheres"

        val parans = Bundle()
        parans.putString("nome",mulher)
        intent.putExtras(parans)
    }

    //funcao Botão empresas para tela cadastro
    fun clickEmpresas(){
        var intent = Intent(this , CadastroGeralActivity::class.java)
        startActivity(intent)
    }

    //funcao Botão mulheres para tela cadastro
    fun clickMulheres(){
        var intent = Intent(this , CadastroGeralActivity::class.java)
        startActivity(intent)
    }


    //funcao Botão Sair
    fun clickSair() {
        val returnIntent = Intent()
        returnIntent.putExtra("result", "Bye App FemmeIt")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(this, "Clicou em configurações", Toast.LENGTH_LONG).show()
           //entra na tela de Configuração
            var intent = Intent(this , TelaConfigActivity::class.java)
            startActivity(intent)
        } else if (id == R.id.action_adiconar) {
            Toast.makeText(this, "Clicou em Adiconar", Toast.LENGTH_LONG).show()
            //entra na tela de cadastro
          var intent = Intent(this , TelaCadastroActivity::class.java)
          startActivity(intent)
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
