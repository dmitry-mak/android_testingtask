package alfa.system.binreader.data.repository

import alfa.system.binreader.data.local.dao.BinSearchHistoryDao
import alfa.system.binreader.data.local.entity.BankInfoEmbedded
import alfa.system.binreader.data.local.entity.BinSearchHistoryEntity
import alfa.system.binreader.data.local.entity.CardNumberEmbedded
import alfa.system.binreader.data.local.entity.CountryEmbedded
import alfa.system.binreader.data.remote.api.BinApiService
import alfa.system.binreader.data.remote.dto.BinInfoDto
import alfa.system.binreader.domain.model.BankInfo
import alfa.system.binreader.domain.model.BinInfo
import alfa.system.binreader.domain.model.CardNumberInfo
import alfa.system.binreader.domain.model.CountryInfo
import alfa.system.binreader.domain.repository.BinRepository
import kotlin.String

class BinRepositoryImpl(private val api: BinApiService, private val historyDao: BinSearchHistoryDao) : BinRepository {

    override suspend fun getBinInfo(binNumber: String): BinInfo {
        val dto = api.getBinInfo(binNumber)
        val domain = dto.toDomain()

    historyDao.insert(
        BinSearchHistoryEntity(
            binNumber = binNumber,
            timestamp = System.currentTimeMillis(),
            scheme = domain.scheme,
            type= domain.type,
            brand = domain.brand,
            prepaid = domain.prepaid,
            number = domain.number?.let { CardNumberEmbedded(length = it.length, luhn = it.luhn) },
            country = domain.country?.let {
                CountryEmbedded(
                    numeric = it.numeric,
                    name = it.name,
                    emoji = it.emoji,
                    currency = it.currency,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    alpha2 = it.alpha2
                )
            },
            bank = domain.bank?.let {
                BankInfoEmbedded(
                    name = it.name,
                    url = it.url,
                    phone = it.phone,
                    city = it.city
                )
            }
        )
    )
        return domain
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
                alpha2 = it.alpha2
            )
        },
        bank = bank?.let {
            BankInfo(
                name = it.name,
                url = it.url,
                phone = it.phone,
                city = it.city
            )
        }
    )
}
