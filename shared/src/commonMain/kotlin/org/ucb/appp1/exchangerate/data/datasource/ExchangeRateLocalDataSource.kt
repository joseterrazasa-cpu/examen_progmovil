package org.ucb.appp1.exchangerate.data.datasource

import org.ucb.appp1.exchangerate.data.dao.ExchangeRateDao
import org.ucb.appp1.exchangerate.data.mapper.toEntity
import org.ucb.appp1.exchangerate.data.mapper.toModel
import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

class ExchangeRateLocalDataSource(
    private val dao: ExchangeRateDao
) {
    suspend fun getList(): List<ExchangeRateModel> {
        return dao.getList().map { it.toModel() }
    }

    suspend fun save(rate: ExchangeRateModel) {
        dao.save(rate.toEntity())
    }

    suspend fun getById(id: String): ExchangeRateModel? {
        return dao.getById(id)?.toModel()
    }

    suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }
}
