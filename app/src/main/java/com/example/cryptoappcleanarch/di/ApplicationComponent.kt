package com.example.cryptoappcleanarch.di

import android.app.Application
import com.example.cryptoappcleanarch.presentation.CoinApp
import com.example.cryptoappcleanarch.presentation.CoinDetailFragment
import com.example.cryptoappcleanarch.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(coinDetailFragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
