package com.example.appfemmeit

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.toolbar.*

class PerfilActivity : AppCompatActivity() {

    private val context: Context get() = this
    var perfil: Perfil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        if (intent.getSerializableExtra("perfil") is Perfil)
            perfil = intent.getSerializableExtra("perfil") as Perfil

        setSupportActionBar(toolbar)
        supportActionBar?.title = perfil?.nome
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nomeUsuario.text = perfil?.nome
        Picasso.with(this).load(perfil?.img).fit().into(imagemPerfil,
            object : com.squareup.picasso.Callback {
                override fun onSuccess() {}

                override fun onError() {}
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu_perfil, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_remover) {
            AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Deseja excluir o Usuario")
                .setPositiveButton("Sim") { dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") { dialog, which ->
                    dialog.dismiss()
                }.create().show()
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.perfil != null && this.perfil is Perfil) {
            Thread {
                PerfilService.delete(this.perfil as Perfil)
                runOnUiThread {
                    finish()
                }
            }.start()
        }
    }
}

