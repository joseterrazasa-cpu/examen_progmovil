package org.ucb.appp1.exchangerate.presentation.viewmodel

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

data class ExchangeRateState(
    val availableRates: List<ExchangeRateModel> = emptyList(),
    val savedRates: List<ExchangeRateModel> = emptyList(),
    val selectedTab: Int = 0,
    val inputCurrency: String = "",
    val inputPlural: String = "",
    val inputRate: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
