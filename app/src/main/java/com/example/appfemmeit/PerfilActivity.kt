package com.example.appfemmeit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
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
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}

