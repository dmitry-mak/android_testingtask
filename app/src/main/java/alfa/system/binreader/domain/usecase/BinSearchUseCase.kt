package alfa.system.binreader.domain.usecase

import alfa.system.binreader.domain.model.BinInfo
import alfa.system.binreader.domain.repository.BinRepository

class BinSearchUseCase(
    private val binRepository: BinRepository){
    suspend operator fun invoke (binNumber: String): BinInfo{
        val cleaned = binNumber.trim()
        require(cleaned.isNotEmpty()){"BIN could not be empty"}
        require(cleaned.all { it.isDigit() }) {"BIN must contain only digits"}
        require(cleaned.length in 6..8){"Invalid BIN length. Should be 6-8 digits"}

        return binRepository.getBinInfo(cleaned)
    }
}