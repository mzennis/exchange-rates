package id.mzennis.rates.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import id.mzennis.rates.data.model.DataMapper
import id.mzennis.rates.data.model.ExchangeRate
import id.mzennis.rates.util.keyLastUpdated
import id.mzennis.rates.util.keyRates
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val mapper: DataMapper,
) {

    suspend fun getExchangeRate(): ExchangeRate {
        val pref = dataStore.data.first()
        val data = pref[keyRates].orEmpty()
        return ExchangeRate(
            data = mapper.fromJson(data),
            lastUpdated = pref[keyLastUpdated] ?: 0L
        )
    }

    suspend fun save(exchangeRates: Map<String, Double>, lastUpdated: Long) {
        dataStore.edit { pref ->
            pref[keyRates] = mapper.toJson(exchangeRates)
            pref[keyLastUpdated] = lastUpdated
        }
    }
}