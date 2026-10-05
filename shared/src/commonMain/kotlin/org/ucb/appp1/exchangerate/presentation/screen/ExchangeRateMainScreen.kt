package org.ucb.appp1.exchangerate.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.exchangerate.presentation.viewmodel.ExchangeRateEvent
import org.ucb.appp1.exchangerate.presentation.viewmodel.ExchangeRateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRateMainScreen(
    viewModel: ExchangeRateViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tipo de Cambio Dólar (Room KMP)") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(selectedTabIndex = state.selectedTab) {
                Tab(
                    selected = state.selectedTab == 0,
                    onClick = { viewModel.emitEvent(ExchangeRateEvent.OnTabSelected(0)) },
                    text = { Text("Cotizaciones") }
                )
                Tab(
                    selected = state.selectedTab == 1,
                    onClick = { viewModel.emitEvent(ExchangeRateEvent.OnTabSelected(1)) },
                    text = { Text("Guardadas (${state.savedRates.size})") }
                )
            }

            when (state.selectedTab) {
                0 -> ExchangeRateListScreen(
                    state = state,
                    onEvent = viewModel::emitEvent
                )
                1 -> SavedExchangeRatesScreen(
                    state = state,
                    onEvent = viewModel::emitEvent
                )
            }
        }
    }
}
