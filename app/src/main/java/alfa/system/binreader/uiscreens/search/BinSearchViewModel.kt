package alfa.system.binreader.uiscreens.search

import alfa.system.binreader.domain.usecase.BinSearchUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class BinSearchUiState(
    val binNumber: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val resultText: String = "",
)

class BinSearchViewModel(
    private val binSearchUseCase: BinSearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BinSearchUiState())
    val uiState: StateFlow<BinSearchUiState> = _uiState

    fun onBinNumberChange(newBinNumber: String) {
        _uiState.update { it.copy(binNumber = newBinNumber, error = null) }
    }


    fun onSearchClicked() {
        val binNumber = uiState.value.binNumber
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, resultText = "") }
            try {
                val info = binSearchUseCase(binNumber)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        resultText = buildString {
                            appendLine("Scheme: ${info.scheme}")
                            appendLine("Type: ${info.type}")
                            appendLine("Brand: ${info.brand}")
                            appendLine("Prepaid: ${info.prepaid}")
                            appendLine("Country: ${info.country?.name}")
                            appendLine("Coordinates: ${info.country?.latitude}, ${info.country?.longitude}")
                            appendLine("Bank: ${info.bank?.name}")
                            appendLine("URL: ${info.bank?.url}")
                            appendLine("Phone: ${info.bank?.phone}")
                            appendLine("City: ${info.bank?.city}")
                        }.trim()
                    )
                }
            } catch (t: Throwable) {
                _uiState.update { it.copy(isLoading = false, error = t.message ?: "Unknown error") }
            }
        }
    }
}