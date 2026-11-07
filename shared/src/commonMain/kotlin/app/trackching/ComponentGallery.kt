package app.trackching

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Temporary: shows the component kit so we can check it on the phone. */
@Composable
fun ComponentGallery() {
    val c = TrackchingTheme.colors
    var picked by remember { mutableStateOf("Cash") }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
    ) {
        Tag("Events")
        Tag("Top", second = "Expenses")

        OffsetBlock(Modifier.fillMaxWidth()) {
            Text(
                "ADD ACCOUNT",
                Modifier.padding(12.dp),
                color = c.onOrange,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }

        Bar(fraction = 0.62f, label = "Food & Drinks") {
            Text(
                formatAmount(123_200L, AmountKind.Expense),
                color = c.onOrange,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text("62%", color = c.onOrange, fontWeight = FontWeight.Bold)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Cash", "BPI", "GCash").forEach { name ->
                Chip(name, selected = name == picked, onClick = { picked = name }, dot = c.orange)
            }
        }
    }
}