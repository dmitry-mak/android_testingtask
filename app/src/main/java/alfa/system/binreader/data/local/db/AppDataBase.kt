package alfa.system.binreader.data.local.db

import alfa.system.binreader.data.local.dao.BinSearchHistoryDao
import alfa.system.binreader.data.local.entity.BinSearchHistoryEntity
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [BinSearchHistoryEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun binSearchHistoryDao(): BinSearchHistoryDao
}
