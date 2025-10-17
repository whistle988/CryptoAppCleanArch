package com.example.cryptoappcleanarch.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoappcleanarch.data.network.model.CoinInfoDto
import com.example.cryptoappcleanarch.data.repository.CoinRepositoryImpl
import com.example.cryptoappcleanarch.domain.GetCoinInfoListUseCase
import com.example.cryptoappcleanarch.domain.GetCoinInfoUseCase
import com.example.cryptoappcleanarch.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}
