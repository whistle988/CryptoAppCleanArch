package com.example.cryptoappcleanarch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cryptoappcleanarch.data.database.AppDatabase
import com.example.cryptoappcleanarch.domain.CoinInfo
import com.example.cryptoappcleanarch.domain.CoinRepository
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoappcleanarch.data.database.CoinInfoDao
import com.example.cryptoappcleanarch.data.mapper.CoinMapper
import com.example.cryptoappcleanarch.data.workers.RefreshDataWorker
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao: CoinInfoDao,
    private val mapper: CoinMapper
): CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        coinInfoDao.getPriceList().map {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}
