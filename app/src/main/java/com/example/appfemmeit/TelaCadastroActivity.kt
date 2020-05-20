package com.example.appfemmeit

import android.os.Bundle
import android.view.FocusFinder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_tela_cadastro.*


class TelaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)
        setTitle("Novo Usuário")

        ///seta de voltar na toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //validação campo nome
        val textInputLayoutnome = findViewById<TextInputLayout>(R.id.nome)
        val editTextcamponomecompleto = textInputLayoutnome.editText
        adicionaValidação(editTextcamponomecompleto)

        //validação campo profissão
        val textInputLayoutprofissao = findViewById<TextInputLayout>(R.id.profissao)
        val editTextcampoprofissao = textInputLayoutprofissao.editText
        adicionaValidação(editTextcampoprofissao)

        //validação campo telefone
        val textInputLayouttelefone = findViewById<TextInputLayout>(R.id.telefone)
        val editTextcampotelefone = textInputLayouttelefone.editText
        adicionaValidação(editTextcampotelefone)

        //validação email
        val textInputLayoutemail = findViewById<TextInputLayout>(R.id.email)
        val editTextcampoemail = textInputLayoutemail.editText
        adicionaValidação(editTextcampoemail)

        //validaçao login
        val textInputLayoutlogin = findViewById<TextInputLayout>(R.id.login)
        val editTextcampologin = textInputLayoutlogin.editText
        adicionaValidação(editTextcampologin)

        //validação senha
        val textInputLayoutsenha = findViewById<TextInputLayout>(R.id.senha)
        val editTextcamposenha = textInputLayoutsenha.editText
        adicionaValidação(editTextcamposenha)

        button_salvar.setOnClickListener {
            val perfil = Perfil()
            perfil.nome = editTextcamponomecompleto?.text.toString()
            perfil.email = editTextcampoemail?.text.toString()
           // perfil.dtNascimento = edit.editText.toString()
            perfil.telefone1 = editTextcampotelefone?.text.toString()
            perfil.profissao = editTextcampoprofissao?.text.toString()
            perfil.login = editTextcampologin?.text.toString()
            perfil.senha = editTextcamposenha?.text.toString()

            taskAtualizar(perfil)
        }
    }


    fun adicionaValidação(campo: EditText?) {
        campo?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (campo.text.toString().isEmpty()) {
                    campo.setError("Campo Obrigatorio")
                }
            }
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



