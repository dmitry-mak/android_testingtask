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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BinSearchScreen(
    onOpenHistory: () -> Unit,
) {
    val (bin, setBin) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(PaddingValues(16.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),)
        {
            Text(text = "BIN number search")

            OutlinedTextField(
                value = bin,
                onValueChange = setBin,
                label = { Text("BIN") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Button(
                onClick = {
//    TODO: подключить viewModel и Retrofit/mock
                },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Search") }
            Button(
                onClick = onOpenHistory,
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Open history") }
            Text(text = "Result: ")
        }
    }