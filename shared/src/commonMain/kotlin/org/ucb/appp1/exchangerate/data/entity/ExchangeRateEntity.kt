package org.ucb.appp1.exchangerate.data.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRateEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "rate")
    val rate: Double,

    @ColumnInfo(name = "plural")
    val plural: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = 0L
)
