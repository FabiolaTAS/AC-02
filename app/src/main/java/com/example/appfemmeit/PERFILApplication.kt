package com.example.appfemmeit

import android.app.Application
import java.lang.IllegalStateException

class PERFILApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        appInstance = this;

    }

    companion object{

        private var appInstance: PERFILApplication? = null

        fun getInstance(): PERFILApplication{

            if(appInstance == null){
                throw IllegalStateException("Configurar application no Android Manifest")
            }
                return  appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}