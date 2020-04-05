package com.example.appfemmeit

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class DebugActivity: AppCompatActivity() {

    private val Tag = "FEMMEIT"
    private val className : String
    get() {
        val s = javaClass.name
        return s.substring(s.lastIndexOf("."))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Tag,"$className.onCreate() chamando")
    }

    override fun  onStart(){
        super.onStart()
        Log.d(Tag,"$className.onStart(),chamando")
    }

    override fun  onRestart(){
        super.onRestart()
        Log.d(Tag,"$className.onRestart(),chamando")
    }

    override fun  onResume(){
        super.onResume()
        Log.d(Tag,"$className.onResume(),chamando")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Tag,"$className.onStop(),chamando")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Tag,"$className.onDestroy(),chamando")
    }
}
