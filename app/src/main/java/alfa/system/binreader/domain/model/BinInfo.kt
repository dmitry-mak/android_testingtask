package alfa.system.binreader.domain.model

data class BinInfo(
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val number: CardNumberInfo?,
    val country: CountryInfo?,
    val bank: BankInfo?
)

data class CardNumberInfo(
    val length: Int?,
    val luhn: Boolean?
)
data class CountryInfo(
    val numeric: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?,
    val alpha2: String?
)

data class BankInfo(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)