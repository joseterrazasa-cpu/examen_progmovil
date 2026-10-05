package org.ucb.appp1.exchangerate.domain.usecase

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel
import org.ucb.appp1.exchangerate.domain.repository.ExchangeRateRepository

class AddCustomRateUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(rate: ExchangeRateModel) {
        repository.addCustomRate(rate)
    }
}
