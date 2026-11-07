package app.trackching

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import trackching.shared.generated.resources.Res
import trackching.shared.generated.resources.ic_backspace

private val KeyHeight = 46.dp
private val Gap = 6.dp

private val DigitRows = listOf(
    listOf(Key.Digits("1"), Key.Digits("2"), Key.Digits("3")),
    listOf(Key.Digits("4"), Key.Digits("5"), Key.Digits("6")),
    listOf(Key.Digits("7"), Key.Digits("8"), Key.Digits("9")),
    listOf(Key.Dot, Key.Digits("0"), Key.Digits("00")),
)

/**
 * The amount keypad: digits on the left; delete, clear and a tall SAVE on the right.
 * It only reports presses. The screen that uses it keeps the amount (see press()).
 */
@Composable
fun Keypad(
    onKey: (Key) -> Unit,
    onSave: () -> Unit,
    saveEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val c = TrackchingTheme.colors
    Row(
        modifier
            .fillMaxWidth()
            .background(c.band)
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(Gap),
    ) {
        Column(Modifier.weight(3f), verticalArrangement = Arrangement.spacedBy(Gap)) {
            DigitRows.forEach { row ->
                Row(horizontalArrangement = Arrangement.spacedBy(Gap)) {
                    row.forEach { key ->
                        KeyButton(Modifier.weight(1f).height(KeyHeight), c.field, onClick = { onKey(key) }) {
                            Text(
                                if (key is Key.Digits) key.text else ".",
                                color = c.ink,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(Gap)) {
            KeyButton(Modifier.fillMaxWidth().height(KeyHeight), c.field, onClick = { onKey(Key.Back) }) {
                Icon(painterResource(Res.drawable.ic_backspace), contentDescription = "Delete last digit", tint = c.ink2)
            }
            KeyButton(
                Modifier.fillMaxWidth().height(KeyHeight).semantics { contentDescription = "Clear amount" },
                c.field,
                onClick = { onKey(Key.Clear) },
            ) {
                Text("C", color = c.ink2, fontSize = 17.sp, fontWeight = FontWeight.Bold)
            }
            KeyButton(
                Modifier.fillMaxWidth().height(KeyHeight * 2 + Gap),
                if (saveEnabled) c.orange else c.line,
                onClick = onSave,
                enabled = saveEnabled,
            ) {
                Text(
                    "SAVE",
                    color = if (saveEnabled) c.onOrange else c.ink2,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
    }
}

@Composable
private fun KeyButton(
    modifier: Modifier,
    color: Color,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier.background(color).clickable(enabled = enabled, role = Role.Button, onClick = onClick),
        contentAlignment = Alignment.Center,
        content = content,
    )
}