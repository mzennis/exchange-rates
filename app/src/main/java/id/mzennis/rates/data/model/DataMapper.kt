package id.mzennis.rates.data.model

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DataMapper @Inject constructor() {

    fun toJson(data: Map<String, Double>): String {
        return Json.encodeToString(data)
    }

    fun fromJson(json: String): Map<String, Double> {
        return Json.decodeFromString(json
        )
    }
}