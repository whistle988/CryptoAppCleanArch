package com.example.cryptoappcleanarch.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoappcleanarch.data.workers.RefreshDataWorkerFactory
import com.example.cryptoappcleanarch.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
