package org.ucb.appp1.exchangerate.presentation.viewmodel

import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel

sealed interface ExchangeRateEvent {
    data class OnTabSelected(val index: Int) : ExchangeRateEvent
    data class OnSaveRate(val rate: ExchangeRateModel) : ExchangeRateEvent
    object OnAddCustomRate : ExchangeRateEvent
    data class OnInputCurrencyChange(val value: String) : ExchangeRateEvent
    data class OnInputPluralChange(val value: String) : ExchangeRateEvent
    data class OnInputRateChange(val value: String) : ExchangeRateEvent
    data class OnDeleteSavedRate(val id: String) : ExchangeRateEvent
}
