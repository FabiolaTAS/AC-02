package com.example.appfemmeit

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var perfils = listOf<Perfil>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)


        //setando valores
        var args = intent.extras

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Usuarios Cadastrados"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ConfiguraMenuLateral()

        recyclerPerfil?.layoutManager = LinearLayoutManager(context)
        recyclerPerfil?.itemAnimator = DefaultItemAnimator()
        recyclerPerfil?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskPerfil()
    }

    fun taskPerfil() {

        Thread {
            this.perfils = PerfilService.getPerfils(context)
            runOnUiThread {
                recyclerPerfil?.adapter = PerfilAdapter(perfils) { onClickPerfils(it) }
                enviaNotificacao(perfils.get(2))
            }
        }.start()
    }


    fun enviaNotificacao(perfil: Perfil) {
        val intent = Intent(this, PerfilActivity::class.java)
        intent.putExtra("perfil", perfil)
        NotificationUtil.create(
            this,
            1,
            intent,
            "APP - FemmeIt",
            "Um novo Usuario foi cadastrado, verifique o perfil dele ${perfil.nome}"
        )
    }

    fun onClickPerfils(perfil: Perfil) {
        Toast.makeText(context, "Clicou em ${perfil.nome}", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, PerfilActivity::class.java)
        intent.putExtra("perfil", perfil)
        startActivity(intent)
    }

    //funcao Botão Sair
    fun clickSair() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {

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

        if (id == R.id.action_adiconar) {
            //entra na tela de cadastro
            var intent = Intent(this, TelaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(this, "Clicou em atualizar", Toast.LENGTH_LONG).show()
            val intent = Intent(context, TelaCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            // atualizar lista de perfil
            taskPerfil()
        }
    }

    private fun ConfiguraMenuLateral() {
        var toggle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.nav_pen,
            R.string.nav_close
        )
        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()

        menu_lateral.setNavigationItemSelectedListener(
            this
        )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_perfil -> {
                Toast.makeText(this, "Clicou em perfil", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_forum -> {
                Toast.makeText(this, "Clicou em Forum", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_localizacao -> {
                //Toast.makeText(this, "Clicou em Localização", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, MapasActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_sairApp -> {
                Toast.makeText(this, "Ate logo", Toast.LENGTH_SHORT).show()
                clickSair()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }

}


