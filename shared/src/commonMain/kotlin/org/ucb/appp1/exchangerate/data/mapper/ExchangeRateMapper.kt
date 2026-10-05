package org.ucb.appp1.exchangerate.data.mapper

import org.ucb.appp1.exchangerate.data.dto.ExchangeRateDto
import org.ucb.appp1.exchangerate.data.entity.ExchangeRateEntity
import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

fun ExchangeRateDto.toModel(): ExchangeRateModel = ExchangeRateModel(
    id = id,
    currency = currency,
    rate = rate,
    plural = plural,
    timestamp = timestamp
)

fun ExchangeRateEntity.toModel(): ExchangeRateModel = ExchangeRateModel(
    id = id,
    currency = currency,
    rate = rate,
    plural = plural,
    timestamp = timestamp
)

fun ExchangeRateModel.toEntity(): ExchangeRateEntity = ExchangeRateEntity(
    id = id,
    currency = currency,
    rate = rate,
    plural = plural,
    timestamp = if (timestamp == 0L) 1727892000000L else timestamp
)
