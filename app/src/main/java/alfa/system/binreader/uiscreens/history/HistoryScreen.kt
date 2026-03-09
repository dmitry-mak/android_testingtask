package alfa.system.binreader.uiscreens.history

import alfa.system.binreader.utils.DateUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun HistoryScreen(
    onBack: () -> Unit,
) {
    val viewModel: HistoryViewModel = koinViewModel()
    val items by viewModel.items.collectAsState()

    Column(
        modifier = Modifier
            .padding(PaddingValues(16.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(text = "History")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Button(onClick = onBack) {
                Text("Back")
            }
            Button(
                onClick = { viewModel.clearHistory() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Clear history")
            }
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(items) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "BIN number: ${item.binNumber}")
                        Text(text = "Scheme: ${item.scheme ?: "-"} Type: ${item.type ?: "-"}")
                        Text(text = "Country: ${item.country?.name ?: "-"}")
                        Text(text = "Bank: ${item.bank?.name ?: "-"}")
                        Text(text = "Searched at: ${DateUtils.formatDateStamp(item.timestamp)}")
                    }
                }
            }
        }
    }
}