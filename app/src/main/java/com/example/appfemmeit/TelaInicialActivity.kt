package com.example.appfemmeit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)



        //setando valores
        var args = intent.extras
        val nome = args?.getString("nome")

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tela Incial"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        atualizando.visibility = View.INVISIBLE

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
        val parans = Bundle()
        parans.putString("parametro","Comunidade")
        intent.putExtras(parans)

        startActivity(intent)
    }

    //funcao Botão empresas para tela cadastro
    fun clickEmpresas(){
        var intent = Intent(this , CadastroGeralActivity::class.java)

        val parans = Bundle()
        parans.putString("parametro","Empresa")
        intent.putExtras(parans)

        startActivity(intent)
    }

    //funcao Botão mulheres para tela cadastro
    fun clickMulheres(){
        var intent = Intent(this , CadastroGeralActivity::class.java)
        val parans = Bundle()
        parans.putString("parametro","Mulheres")
        intent.putExtras(parans)

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
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(context, newText, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em atualizar", Toast.LENGTH_LONG).show()
            atualizando.visibility = View.VISIBLE
            Handler().postDelayed({
                    atualizando.setVisibility(View.INVISIBLE)
            }, 10000)

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
