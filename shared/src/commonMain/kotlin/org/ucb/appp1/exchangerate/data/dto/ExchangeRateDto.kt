package org.ucb.appp1.exchangerate.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateDto(
    @SerialName("id")
    val id: String,
    @SerialName("currency")
    val currency: String,
    @SerialName("rate")
    val rate: Double,
    @SerialName("plural")
    val plural: String,
    @SerialName("timestamp")
    val timestamp: Long = 0L
)
