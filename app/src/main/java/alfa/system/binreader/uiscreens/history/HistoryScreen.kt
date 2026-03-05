package alfa.system.binreader.uiscreens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HistoryScreen (
    onBack: () -> Unit,
){
    Column(
        modifier = Modifier
            .padding(PaddingValues(16.dp))
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ){
        Text(text = "History")
        Button(onClick = onBack){
            Text("Back")
        }
    }
}