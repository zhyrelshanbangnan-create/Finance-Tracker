package app.trackching

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.trackching.db.TrackchingDatabase

@Composable
fun App(database: TrackchingDatabase) {
    val queries = database.savedAmountQueries

    var input by remember { mutableStateOf("") }
    var saved by remember { mutableStateOf(queries.latest().executeAsOneOrNull()) }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding().padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Hello Trackching", style = MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Amount (e.g. 325.00)") },
            )

            Button(onClick = {
                val centavos = parseCentavos(input)
                if (centavos != null) {
                    queries.save(centavos)
                    saved = centavos
                    input = ""
                }
            }) {
                Text("Save")
            }

            saved?.let { Amount(centavos = it, kind = AmountKind.Expense) }
        }
    }
}