package alfa.system.binreader.data.repository

import alfa.system.binreader.data.remote.dto.BinInfoDto
import alfa.system.binreader.domain.model.BankInfo
import alfa.system.binreader.domain.model.BinInfo
import alfa.system.binreader.domain.model.CardNumberInfo
import alfa.system.binreader.domain.model.CountryInfo
import alfa.system.binreader.domain.repository.BinRepository
import kotlinx.serialization.json.Json

class MockBinRepository(
    private val json: Json,
) : BinRepository {
    override suspend fun getBinInfo(binNumber: String): BinInfo {
        val dto = json.decodeFromString(
            deserializer = BinInfoDto.serializer(),
            string = SAMPLE_JSON
        )
        return dto.toDomain()
    }

    private fun BinInfoDto.toDomain(): BinInfo = BinInfo(
        scheme = scheme,
        type = type,
        brand = brand,
        prepaid = prepaid,
        number = number?.let {
            CardNumberInfo(
                length = it.length,
                luhn = it.luhn
            )
        },
        country = country?.let {
            CountryInfo(
                numeric = it.numeric,
                name = it.name,
                emoji = it.emoji,
                currency = it.currency,
                latitude = it.latitude,
                longitude = it.longitude,
                alpha2 = it.alpha2,
            )
        },
        bank = bank?.let {
            BankInfo(
                name = it.name,
                url = it.url,
                phone = it.phone,
                city = it.city,
            )
        }
    )

    private companion object {
        private const val SAMPLE_JSON: String = """
            {
              "number": {
                        "length": 16,
                        "luhn": true},
              "scheme": "visa",
              "type": "debit",
              "brand": "Visa/Dankort",
              "prepaid": false,
              "country": {
                "numeric": "208",
                "alpha2": "DK",
                "name": "Denmark",
                "emoji": "🇩🇰",
                "currency": "DKK",
                "latitude": 56,
                "longitude": 10
              },
              "bank": {
                "name": "Jyske Bank",
                "url": "www.jyskebank.dk",
                "phone": "+4589893300",
                "city": "Hjørring"
              }
            }"""
    }
}