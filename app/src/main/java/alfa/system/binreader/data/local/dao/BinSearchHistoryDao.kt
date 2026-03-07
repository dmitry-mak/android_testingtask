package alfa.system.binreader.data.local.dao

import alfa.system.binreader.data.local.entity.BinSearchHistoryEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface BinSearchHistoryDao {

    @Query("SELECT * FROM bin_search_history ORDER BY timestamp DESC")
    fun getAll(): Flow<List<BinSearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: BinSearchHistoryEntity)

    @Query("DELETE FROM bin_search_history")
    suspend fun clearAll()


}