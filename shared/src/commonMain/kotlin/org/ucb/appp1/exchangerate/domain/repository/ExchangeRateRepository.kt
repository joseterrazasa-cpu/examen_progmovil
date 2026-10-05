package org.ucb.appp1.exchangerate.domain.repository

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

interface ExchangeRateRepository {
    suspend fun getAvailableRates(): List<ExchangeRateModel>
    suspend fun getSavedRates(): List<ExchangeRateModel>
    suspend fun saveRate(rate: ExchangeRateModel)
    suspend fun addCustomRate(rate: ExchangeRateModel)
    suspend fun deleteSavedRate(id: String)
}
