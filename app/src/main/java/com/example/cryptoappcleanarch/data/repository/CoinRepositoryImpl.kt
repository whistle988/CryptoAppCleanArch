package com.example.cryptoappcleanarch.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.cryptoappcleanarch.data.database.AppDatabase
import com.example.cryptoappcleanarch.domain.CoinInfo
import com.example.cryptoappcleanarch.domain.CoinRepository
import androidx.lifecycle.map
import com.example.cryptoappcleanarch.data.mapper.CoinMapper
import com.example.cryptoappcleanarch.data.network.ApiFactory
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
): CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val mapper = CoinMapper()

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

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}
