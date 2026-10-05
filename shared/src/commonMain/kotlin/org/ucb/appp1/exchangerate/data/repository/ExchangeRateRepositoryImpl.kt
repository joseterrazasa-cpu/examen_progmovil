package org.ucb.appp1.exchangerate.data.repository

import org.ucb.appp1.exchangerate.data.datasource.ExchangeRateLocalDataSource
import org.ucb.appp1.exchangerate.data.datasource.ExchangeRateRemoteDataSource
import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel
import org.ucb.appp1.exchangerate.domain.repository.ExchangeRateRepository

class ExchangeRateRepositoryImpl(
    private val local: ExchangeRateLocalDataSource,
    private val remote: ExchangeRateRemoteDataSource
) : ExchangeRateRepository {

    override suspend fun getAvailableRates(): List<ExchangeRateModel> {
        return remote.getAvailableRates()
    }

    override suspend fun getSavedRates(): List<ExchangeRateModel> {
        return local.getList()
    }

    override suspend fun saveRate(rate: ExchangeRateModel) {
        local.save(rate)
    }

    override suspend fun addCustomRate(rate: ExchangeRateModel) {
        remote.addCustomRate(rate)
    }

    override suspend fun deleteSavedRate(id: String) {
        local.deleteById(id)
    }
}
