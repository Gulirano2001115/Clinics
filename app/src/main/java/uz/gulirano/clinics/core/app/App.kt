package uz.gulirano.clinics.core.app

import android.app.Application

class App : Application() {

    companion object {
        var context: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}