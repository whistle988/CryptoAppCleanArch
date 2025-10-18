package com.example.cryptoappcleanarch.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoappcleanarch.domain.CoinInfo

object CoinInfoDiffCallback: DiffUtil.ItemCallback<CoinInfo>() {
    // проверка на один и тот же объект
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }
    // проверка на изменение содержимого, если изменилось то перерисовывает
    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}
