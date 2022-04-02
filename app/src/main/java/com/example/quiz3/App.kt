package com.example.quiz3

import android.app.Application
import com.example.quiz3.di.ServiceLocator

class App: Application() {

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(this)
    }

}