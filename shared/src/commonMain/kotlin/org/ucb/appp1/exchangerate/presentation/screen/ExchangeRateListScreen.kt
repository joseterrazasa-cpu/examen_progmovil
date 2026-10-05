package org.ucb.appp1.exchangerate.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.ucb.appp1.exchangerate.domain.model.ExchangeRateModel
import org.ucb.appp1.exchangerate.presentation.viewmodel.ExchangeRateEvent
import org.ucb.appp1.exchangerate.presentation.viewmodel.ExchangeRateState

@Composable
fun ExchangeRateListScreen(
    state: ExchangeRateState,
    onEvent: (ExchangeRateEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Agregar Cotización del Dólar",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = state.inputCurrency,
                onValueChange = { onEvent(ExchangeRateEvent.OnInputCurrencyChange(it)) },
                label = { Text("Moneda (ej. EUR)") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = state.inputRate,
                onValueChange = { onEvent(ExchangeRateEvent.OnInputRateChange(it)) },
                label = { Text("Tasa (ej. 0.92)") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = state.inputPlural,
            onValueChange = { onEvent(ExchangeRateEvent.OnInputPluralChange(it)) },
            label = { Text("Nombre / Plural (ej. Euros)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onEvent(ExchangeRateEvent.OnAddCustomRate) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar a Cotizaciones")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tipos de Cambio Disponibles (USD -> Monedas)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.availableRates) { rate ->
                ExchangeRateAvailableItem(
                    rate = rate,
                    onSave = { onEvent(ExchangeRateEvent.OnSaveRate(rate)) }
                )
            }
        }
    }
}

@Composable
fun ExchangeRateAvailableItem(
    rate: ExchangeRateModel,
    onSave: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "1 USD = ${rate.rate} ${rate.currency}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = rate.plural,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Button(onClick = onSave) {
                Text("Guardar")
            }
        }
    }
}
