package com.example.appfemmeit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    imagemPerfil.visibility = View.GONE
                }

                override fun onError() {
                    imagemPerfil.visibility = View.GONE
                }
            })
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

}

