package org.ucb.appp1.exchangerate.domain.model

data class ExchangeRateModel(
    val id: String,
    val currency: String,
    val rate: Double,
    val plural: String,
    val timestamp: Long = 0L
)
