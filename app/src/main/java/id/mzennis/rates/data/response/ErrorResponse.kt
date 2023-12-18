package id.mzennis.rates.data.response

import kotlinx.serialization.Serializable

/**
 * Created by meyta.taliti on 04/11/23.
 */
@Serializable
data class ErrorResponse(
    val error: Boolean,
    val status: Int,
    val message: String,
    val description: String,
)