package alfa.system.binreader.uiscreens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun BinSearchScreen(
    onOpenHistory: () -> Unit,
) {
//    val (bin, setBin) = remember { mutableStateOf("") }
    val viewModel: BinSearchViewModel= koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(PaddingValues(16.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),)
        {
            Text(text = "BIN number search")

            OutlinedTextField(
                value = state.binNumber,
//                onValueChange = setBin,
                onValueChange = viewModel::onBinNumberChange,
                label = { Text("BIN") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick =viewModel::onSearchClicked,
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) { Text(if(state.isLoading)"Loading..." else "Search") }

            Button(
                onClick = onOpenHistory,
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open history") }

            if(state.error!=null) {
                Text(text = "Error: ${state.error}")
            }
            Text(text = "Result: ")
            Text(text = state.resultText)
        }
    }