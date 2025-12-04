package com.example.cryptoappcleanarch.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoappcleanarch.data.database.AppDatabase
import com.example.cryptoappcleanarch.data.mapper.CoinMapper
import com.example.cryptoappcleanarch.data.network.ApiFactory
import com.example.cryptoappcleanarch.data.workers.RefreshDataWorkerFactory
import com.example.cryptoappcleanarch.di.DaggerApplicationComponent

class CoinApp: Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
}
