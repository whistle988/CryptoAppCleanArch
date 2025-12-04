package com.example.cryptoappcleanarch.di

import android.app.Application
import com.example.cryptoappcleanarch.data.database.AppDatabase
import com.example.cryptoappcleanarch.data.database.CoinInfoDao
import com.example.cryptoappcleanarch.data.repository.CoinRepositoryImpl
import com.example.cryptoappcleanarch.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}
