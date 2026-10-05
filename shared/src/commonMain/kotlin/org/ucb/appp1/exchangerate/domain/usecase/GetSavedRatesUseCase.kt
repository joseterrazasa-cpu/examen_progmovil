package org.ucb.appp1.exchangerate.domain.usecase

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel
import org.ucb.appp1.exchangerate.domain.repository.ExchangeRateRepository

class GetSavedRatesUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(): List<ExchangeRateModel> {
        return repository.getSavedRates()
    }
}
