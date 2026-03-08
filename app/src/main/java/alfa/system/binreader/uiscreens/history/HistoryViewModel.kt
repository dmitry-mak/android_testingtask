package alfa.system.binreader.uiscreens.history

import alfa.system.binreader.data.local.dao.BinSearchHistoryDao
import alfa.system.binreader.data.local.entity.BinSearchHistoryEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(
   private val dao: BinSearchHistoryDao
) : ViewModel() {

    val history = dao.getAll()
    val items: StateFlow<List<BinSearchHistoryEntity>> =
        history
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun clearHistory(){
        viewModelScope.launch {
            dao.clearAll()
        }
    }
}