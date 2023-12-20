package id.mzennis.rates.data

import id.mzennis.rates.data.model.ExchangeRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateRepository @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val localDataSource: LocalDataSource,
) {
    suspend fun get(): ExchangeRate = withContext(Dispatchers.IO) {
        try {
            val cloud = cloudDataSource.getExchangeRate()
            localDataSource.save(cloud.data, cloud.lastUpdated)
            return@withContext cloud
        } catch (err: Throwable) {
            val local = localDataSource.getExchangeRate()
            if (local.data.isNotEmpty()) {
                return@withContext local
            } else {
                throw err
            }
        }
    }
}