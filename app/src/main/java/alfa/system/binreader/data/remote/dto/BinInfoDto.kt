package alfa.system.binreader.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BinInfoDto(
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: Boolean? = null,
    val country: CountryDto? = null,
    val bank: BankDto? = null,
    val number: NumberDto? = null,
)

@Serializable
data class NumberDto(
    val length: Int? = null,
    val luhn: Boolean? = null
)

@Serializable
data class CountryDto(
    val numeric: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
    @SerialName("alpha2")
    val alpha2: String? = null,
)

@Serializable
data class BankDto(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)