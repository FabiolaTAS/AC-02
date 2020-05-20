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
        var stringnome = editTextcamponomecompleto?.text.toString()

        if (stringnome.isEmpty()) {
            editTextcamponomecompleto?.setError("Campo Obrigatorio")
            editTextcamponomecompleto?.requestFocus()
        }

        //validação campo profissão
        val textInputLayoutprofissao = findViewById<TextInputLayout>(R.id.profissao)
        val editTextcampoprofissao = textInputLayoutprofissao.editText
        var stringprofissao = editTextcampoprofissao?.text.toString()

        if (stringprofissao.isEmpty()) {
            editTextcampoprofissao?.setError("Campo Obrigatorio")
            textInputLayoutprofissao?.requestFocus()
        }

        //validação campo telefone
        val textInputLayouttelefone = findViewById<TextInputLayout>(R.id.telefone)
        val editTextcampotelefone = textInputLayouttelefone.editText
        var stringtelefone = editTextcampotelefone?.text.toString()

        if (stringtelefone.isEmpty()) {
            editTextcampotelefone?.setError("Campo Obrigatorio")
            textInputLayouttelefone?.requestFocus()
        }

        //validação email
        val textInputLayoutemail = findViewById<TextInputLayout>(R.id.email)
        val editTextcampoemail = textInputLayoutemail.editText
        var stringemail = editTextcampoemail?.text.toString()

        if (stringemail.isEmpty()) {
            editTextcampoemail?.setError("Campo Obrigatorio")
            textInputLayoutemail?.requestFocus()
        }

        //validaçao login
        val textInputLayoutlogin = findViewById<TextInputLayout>(R.id.login)
        val editTextcampologin = textInputLayoutlogin.editText
        var stringlogin = editTextcampologin?.text.toString()

        if (stringlogin.isEmpty()) {
            editTextcampologin?.setError("Campo Obrigatorio")
            textInputLayoutlogin?.requestFocus()
        }

        //validação senha
        val textInputLayoutsenha = findViewById<TextInputLayout>(R.id.senha)
        val editTextcamposenha = textInputLayoutsenha.editText
        var stringsenha = editTextcamposenha?.text.toString()

        if (stringsenha.isEmpty()) {
            editTextcamposenha?.setError("Campo Obrigatorio")
            textInputLayoutsenha?.requestFocus()
        }


        button_salvar.setOnClickListener {
            val perfil = Perfil()
            perfil.nome = nome.editText.toString()
            perfil.email = email.editText.toString()
            perfil.dtNascimento = dtNascimento.editText.toString()
            perfil.telefone1 = telefone.editText.toString()
            perfil.profissao = profissao.editText.toString()
            perfil.login = login.editText.toString()
            perfil.senha = senha.editText.toString()

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



