package org.ucb.appp1.exchangerate.domain.usecase

import org.ucb.appp1.exchangerate.domain.repository.ExchangeRateRepository

class DeleteSavedRateUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteSavedRate(id)
    }
}
