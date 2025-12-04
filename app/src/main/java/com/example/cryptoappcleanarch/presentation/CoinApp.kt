package com.example.cryptoappcleanarch.presentation

import android.app.Application
import com.example.cryptoappcleanarch.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
