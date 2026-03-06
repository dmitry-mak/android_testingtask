package alfa.system.binreader.domain.repository

import alfa.system.binreader.domain.model.BinInfo

interface BinRepository{
    suspend fun getBinInfo(binNumber: String): BinInfo
}