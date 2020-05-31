package com.example.appfemmeit

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.caelum.stella.format.CPFFormatter
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_tela_cadastro.*


class TelaCadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)
        setTitle("Novo Usuário")

        ///seta de voltar na toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val editTextcamponomecompleto = configuraNomeCompleto()
        val editTextcampoprofissao = configuraProfissao()
        val editTextcampotelefone = configuraTelefone()
        val editTextcampoemail = configuraEmail()
        val editTextcampologin = configuraLogin()
        val editTextcamposenha = configuraSenha()
        val editTextcamponomeLinkedin = configuraLinkedin()
        val editTexttextInputLayoutGithub = configuraGithub()
        val editTextcamponomeCpf = configuraCampoCpf()
        val editTextcamponomePerfil = configuraPerfil()
        val editTextcamponomeTelSeg = configuraTelSeg()
        val editTextcamponomeDataNas = configuraDataNas()

        button_salvar.setOnClickListener {
            val perfil = Perfil()
            perfil.nome = editTextcamponomecompleto?.text.toString()
            perfil.email = editTextcampoemail?.text.toString()
            perfil.telefone1 = editTextcampotelefone?.text.toString()
            perfil.profissao = editTextcampoprofissao?.text.toString()
            perfil.login = editTextcampologin?.text.toString()
            perfil.senha = editTextcamposenha?.text.toString()
            perfil.linkedin = editTextcamponomeLinkedin?.text.toString()
            perfil.gitHub = editTexttextInputLayoutGithub?.text.toString()
            perfil.cpf = editTextcamponomeCpf.toString()
            perfil.perfil = editTextcamponomePerfil?.text.toString()
            perfil.telefone2 = editTextcamponomeTelSeg?.text.toString()
            perfil.dtNascimento = editTextcamponomeDataNas?.text.toString()
            taskAtualizar(perfil)
        }
    }

    private fun configuraSenha(): EditText? {
        val textInputLayoutsenha = findViewById<TextInputLayout>(R.id.senha)
        val editTextcamposenha = textInputLayoutsenha.editText
        adicionaValidação(textInputLayoutsenha)
        return editTextcamposenha
    }

    private fun configuraLogin(): EditText? {
        val textInputLayoutlogin = findViewById<TextInputLayout>(R.id.login_cadastro)
        val editTextcampologin = textInputLayoutlogin.editText
        adicionaValidação(textInputLayoutlogin)
        return editTextcampologin
    }

    private fun configuraEmail(): EditText? {
        val textInputLayoutemail = findViewById<TextInputLayout>(R.id.email)
        val editTextcampoemail = textInputLayoutemail.editText
        adicionaValidação(textInputLayoutemail)
        return editTextcampoemail
    }

    private fun configuraTelefone(): EditText? {
        val textInputLayouttelefone = findViewById<TextInputLayout>(R.id.telefone_cadastro)
        val editTextcampotelefone = textInputLayouttelefone.editText
        adicionaValidação(textInputLayouttelefone)
        var telefoneComDdd =     textInputLayouttelefone?.toString()
        editTextcampotelefone?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!validaCampoObrigatorio(
                        editTextcampotelefone,
                        textInputLayouttelefone
                    )
                ) return@setOnFocusChangeListener
                if (editTextcampotelefone.length() < 10 || editTextcampotelefone.length() > 11) {
                    textInputLayouttelefone.error = "O celular deve conter de dez a onze digitos"
                }
                formata(telefoneComDdd)
               }else{
                remove(telefoneComDdd)
            }
        }
        removeErro(textInputLayouttelefone)
        return editTextcampotelefone
    }


    fun formata(telefoneComDdd: String?): String? {
        return telefoneComDdd
            ?.replace("([0-9]{2})([0-9]{4,5})([0-9]{4})".toRegex(), "($1) $2-$3")
    }

    fun remove(telefoneComDdd: String?): String? {
        return telefoneComDdd
            ?.replace("(", "")
            ?.replace(")", "")
            ?.replace(" ", "")
            ?.replace("-", "")
    }


    private fun configuraProfissao(): EditText? {
        //validação campo profissão
        val textInputLayoutprofissao = findViewById<TextInputLayout>(R.id.profissao_cadastro)
        val editTextcampoprofissao = textInputLayoutprofissao.editText
        adicionaValidação(textInputLayoutprofissao)
        return editTextcampoprofissao
    }

    private fun configuraNomeCompleto(): EditText? {
        val textInputLayoutnome = findViewById<TextInputLayout>(R.id.nome_cadastro)
        val editTextcamponomecompleto = textInputLayoutnome.editText
        adicionaValidação(textInputLayoutnome)
        return editTextcamponomecompleto
    }

    private fun configuraLinkedin(): EditText? {
        val textInputLayoutLinkedin = findViewById<TextInputLayout>(R.id.linkedin_cadastro)
        val editTextcamponomeLinkedin = textInputLayoutLinkedin.editText
        adicionaValidação(textInputLayoutLinkedin)
        return editTextcamponomeLinkedin
    }

    private fun configuraGithub(): EditText? {
        val textInputLayoutGithub = findViewById<TextInputLayout>(R.id.github_cadastro)
        val editTexttextInputLayoutGithub = textInputLayoutGithub.editText
        adicionaValidação(textInputLayoutGithub)
        return editTexttextInputLayoutGithub
    }

    private fun configuraCampoCpf() {
        val textInputCpf: TextInputLayout = findViewById(R.id.cpf_cadastro)
        val campoCpf = textInputCpf.editText
        val cpfFormatter = CPFFormatter()
        campoCpf!!.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            val cpf = campoCpf.text.toString()
            if (!hasFocus) {
                if (!validaCampoObrigatorio(campoCpf, textInputCpf)) return@OnFocusChangeListener
                if (!validaCampoComOnzeDigitos(campoCpf, textInputCpf)) return@OnFocusChangeListener
                if (!validaCalculoCpf(campoCpf, textInputCpf)) return@OnFocusChangeListener
                removeErro(textInputCpf)
                ///formta CPF com biblioteca Stella
                val cpfFormatado = cpfFormatter.format(cpf)
                campoCpf.setText(cpfFormatado)
            } else {
                try {
                    val cpfSemFormato = cpfFormatter.unformat(cpf)
                    campoCpf.setText(cpfSemFormato)
                } catch (e: IllegalArgumentException) {
                    Log.e("erro formatação cpf", e.message)
                }
            }
        }
    }


    private fun validaCalculoCpf(
        editTextcamponomeCpf: EditText,
        textInputLayoutCpf: TextInputLayout
    ): Boolean {
        try {
            var cpfValidator = CPFValidator()
            cpfValidator.assertValid(editTextcamponomeCpf.text.toString())
        } catch (e: InvalidStateException) {
            textInputLayoutCpf.setError("CPF Inválido")
            return false
        }
        return true
    }

    private fun validaCampoComOnzeDigitos(
        editTextcamponomeCpf: EditText,
        textInputLayoutCpf: TextInputLayout
    ): Boolean {
        if (editTextcamponomeCpf.length() != 11) {
            textInputLayoutCpf.setError("O CPF precisa ter 11 dígitos")
            return false
        }
        return true
    }


    private fun configuraPerfil(): EditText? {
        val textInputLayoutPerfil = findViewById<TextInputLayout>(R.id.perfilCAdastro)
        val editTextcamponomePerfil = textInputLayoutPerfil.editText
        adicionaValidação(textInputLayoutPerfil)
        return editTextcamponomePerfil
    }

    private fun configuraDataNas(): EditText? {
        val textInputLayoutDataNas = findViewById<TextInputLayout>(R.id.dtnascimentoCadastro)
        val editTextcamponomeDataNas = textInputLayoutDataNas.editText
        //  adicionaValidação(textInputLayoutDataNas)
        return editTextcamponomeDataNas
    }

    private fun configuraTelSeg(): EditText? {
        val textInputLayoutTelSeg = findViewById<TextInputLayout>(R.id.telefoneSecundario)
        val editTextcamponomeTelSeg = textInputLayoutTelSeg.editText

        editTextcamponomeTelSeg?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!validaCampoObrigatorio(
                        editTextcamponomeTelSeg,
                        textInputLayoutTelSeg
                    )
                ) return@setOnFocusChangeListener
                if (editTextcamponomeTelSeg.length() < 10 || editTextcamponomeTelSeg.length() > 11) {
                    textInputLayoutTelSeg.error = "O celular deve conter de dez a onze digitos"
                }
            }
        }
        removeErro(textInputLayoutTelSeg)
        return editTextcamponomeTelSeg
    }


    fun adicionaValidação(textInputLayout: TextInputLayout) {
        val campo = textInputLayout.editText
        campo?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (!validaCampoObrigatorio(campo, textInputLayout)) return@setOnFocusChangeListener
                removeErro(textInputLayout)
            }
        }
    }

    private fun validaCampoObrigatorio(
        campo: EditText,
        textInputLayout: TextInputLayout
    ): Boolean {
        if (campo.text.toString().isEmpty()) {
            textInputLayout.setError("Campo Obrigatorio")
            return false
        } else {
            removeErro(textInputLayout)
            return true
        }
    }

    private fun removeErro(textInputLayout: TextInputLayout) {
        textInputLayout.setError(null)
        textInputLayout.isErrorEnabled
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



