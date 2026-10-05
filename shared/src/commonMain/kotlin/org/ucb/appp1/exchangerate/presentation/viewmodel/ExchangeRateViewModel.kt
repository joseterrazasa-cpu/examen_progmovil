package org.ucb.appp1.exchangerate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel
import org.ucb.appp1.exchangerate.domain.usecase.AddCustomRateUseCase
import org.ucb.appp1.exchangerate.domain.usecase.DeleteSavedRateUseCase
import org.ucb.appp1.exchangerate.domain.usecase.GetAvailableRatesUseCase
import org.ucb.appp1.exchangerate.domain.usecase.GetSavedRatesUseCase
import org.ucb.appp1.exchangerate.domain.usecase.SaveExchangeRateUseCase

class ExchangeRateViewModel(
    private val getAvailableRatesUseCase: GetAvailableRatesUseCase,
    private val getSavedRatesUseCase: GetSavedRatesUseCase,
    private val saveExchangeRateUseCase: SaveExchangeRateUseCase,
    private val addCustomRateUseCase: AddCustomRateUseCase,
    private val deleteSavedRateUseCase: DeleteSavedRateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeRateState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ExchangeRateEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val available = getAvailableRatesUseCase()
            val saved = getSavedRatesUseCase()
            _state.update {
                it.copy(
                    availableRates = available,
                    savedRates = saved,
                    isLoading = false
                )
            }
        }
    }

    fun emitEvent(event: ExchangeRateEvent) {
        when (event) {
            is ExchangeRateEvent.OnTabSelected -> {
                _state.update { it.copy(selectedTab = event.index) }
                if (event.index == 1) {
                    loadSavedRates()
                }
            }

            is ExchangeRateEvent.OnInputCurrencyChange -> {
                _state.update { it.copy(inputCurrency = event.value) }
            }

            is ExchangeRateEvent.OnInputPluralChange -> {
                _state.update { it.copy(inputPlural = event.value) }
            }

            is ExchangeRateEvent.OnInputRateChange -> {
                _state.update { it.copy(inputRate = event.value) }
            }

            ExchangeRateEvent.OnAddCustomRate -> {
                val currency = _state.value.inputCurrency.trim().uppercase()
                val plural = _state.value.inputPlural.trim()
                val rateDouble = _state.value.inputRate.toDoubleOrNull()

                if (currency.isBlank() || plural.isBlank() || rateDouble == null) {
                    emitEffect(ExchangeRateEffect.ShowToast("Por favor ingresa moneda, nombre y una tasa válida"))
                    return
                }

                val newRate = ExchangeRateModel(
                    id = "USD_$currency",
                    currency = currency,
                    rate = rateDouble,
                    plural = plural
                )

                viewModelScope.launch {
                    addCustomRateUseCase(newRate)
                    _state.update {
                        it.copy(
                            inputCurrency = "",
                            inputPlural = "",
                            inputRate = ""
                        )
                    }
                    val updatedAvailable = getAvailableRatesUseCase()
                    _state.update { it.copy(availableRates = updatedAvailable) }
                    emitEffect(ExchangeRateEffect.ShowToast("Tipo de cambio agregado a la lista"))
                }
            }

            is ExchangeRateEvent.OnSaveRate -> {
                viewModelScope.launch {
                    saveExchangeRateUseCase(event.rate)
                    loadSavedRates()
                    emitEffect(ExchangeRateEffect.ShowToast("Guardado en la base de datos Room"))
                }
            }

            is ExchangeRateEvent.OnDeleteSavedRate -> {
                viewModelScope.launch {
                    deleteSavedRateUseCase(event.id)
                    loadSavedRates()
                    emitEffect(ExchangeRateEffect.ShowToast("Eliminado de Room"))
                }
            }
        }
    }

    private fun loadSavedRates() {
        viewModelScope.launch {
            val saved = getSavedRatesUseCase()
            _state.update { it.copy(savedRates = saved) }
        }
    }

    private fun emitEffect(effect: ExchangeRateEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
