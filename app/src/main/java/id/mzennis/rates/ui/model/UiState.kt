package id.mzennis.rates.ui.model

import id.mzennis.rates.util.NetworkService

data class UiState(
    val currencyCodes: List<String>,
    val lastUpdated: String,
    val convertedAmount: String,
    val screenState: MainScreenState,
    val appId: String = NetworkService.APP_ID
) {
    companion object {
        val Init = UiState(
            emptyList(),
            "",
            "0.0",
            MainScreenState.Loading
        )
    }
}

sealed interface MainScreenState {
    data object Loading : MainScreenState

    data object Available : MainScreenState

    data class Unavailable(val errorMessage: String) : MainScreenState
}

enum class ScreenState {
    Available, Loading, UnAvailable
}