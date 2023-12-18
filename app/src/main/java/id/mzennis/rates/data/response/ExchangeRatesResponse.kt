package id.mzennis.rates.data.response

import kotlinx.serialization.Serializable

/**
 * Created by meyta.taliti on 28/10/23.
 */
@Serializable
data class ExchangeRatesResponse(
    val base: String,
    val rates: Map<String, Double>
)