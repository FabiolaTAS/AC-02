package com.example.appfemmeit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MapasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapas)

        setTitle("Localização")

    }


    override fun onResume() {
        super.onResume()
        val mapaFragment = MapaFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.layoutMapas, mapaFragment)
            .commit()
    }
}
