package org.ucb.appp1.exchangerate.data.datasource

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

class ExchangeRateRemoteDataSource {
    private val availableRates = mutableListOf(
        ExchangeRateModel("USD_BOB", "BOB", 6.96, "Bolivianos"),
        ExchangeRateModel("USD_EUR", "EUR", 0.92, "Euros"),
        ExchangeRateModel("USD_ARS", "ARS", 980.50, "Pesos Argentinos"),
        ExchangeRateModel("USD_BRL", "BRL", 5.45, "Reales Brasileños"),
        ExchangeRateModel("USD_MXN", "MXN", 19.30, "Pesos Mexicanos"),
        ExchangeRateModel("USD_CLP", "CLP", 930.00, "Pesos Chilenos"),
        ExchangeRateModel("USD_PEN", "PEN", 3.75, "Soles Peruanos"),
        ExchangeRateModel("USD_GBP", "GBP", 0.77, "Libras Esterlinas")
    )

    suspend fun getAvailableRates(): List<ExchangeRateModel> {
        return availableRates.toList()
    }

    suspend fun addCustomRate(rate: ExchangeRateModel) {
        val existingIndex = availableRates.indexOfFirst { it.id == rate.id }
        if (existingIndex >= 0) {
            availableRates[existingIndex] = rate
        } else {
            availableRates.add(0, rate)
        }
    }
}
