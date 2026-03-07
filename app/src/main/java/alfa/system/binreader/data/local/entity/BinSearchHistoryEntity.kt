package alfa.system.binreader.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

// дата класс для описания полей таблицы.
@Entity(tableName = "bin_search_history")
data class BinSearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val binNumber: String,
    val timestamp: Long,

    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,

    @Embedded(prefix = "number_")
    val number: CardNumberEmbedded?,

    @Embedded(prefix = "country_")
    val country: CountryEmbedded?,

    @Embedded(prefix = "bank_")
    val bank: BankInfoEmbedded?
)


data class CardNumberEmbedded(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryEmbedded(
    val numeric: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?,
    val alpha2: String?
)

data class BankInfoEmbedded(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
