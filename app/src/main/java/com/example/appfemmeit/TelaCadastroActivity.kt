package com.example.appfemmeit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_tela_cadastro.*


class TelaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)
        setTitle("Novo Usuário")

        ///seta de voltar na toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button_salvar.setOnClickListener {
            val perfil = Perfil()
            perfil.nome = nomeUsuario.text.toString()
            perfil.email = email.text.toString()
            perfil.dtNascimento = label_nascimento.text.toString()
            perfil.telefone1 = label_telefone.text.toString()
            perfil.img = label_img.text.toString()

            taskAtualizar(perfil)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Clicou em buscar", Toast.LENGTH_LONG).show()
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskAtualizar(perfil: Perfil) {
        Thread {
            PerfilService.save(perfil)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }

}



