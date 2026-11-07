package app.trackching

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import trackching.shared.generated.resources.Res
import trackching.shared.generated.resources.noto_sans_regular

/**
 * Shows an amount as "− ₱ 1,232.00" on one line.
 * If it doesn't fit, the text shrinks (down to 12 sp) instead of being cut off.
 */
@Composable
fun Amount(
    centavos: Long,
    kind: AmountKind,
    modifier: Modifier = Modifier,
    maxFontSize: TextUnit = 32.sp,
) {
    BasicText(
        text = formatAmount(centavos, kind),
        modifier = modifier,
        style = TextStyle(
            fontFamily = FontFamily(Font(Res.font.noto_sans_regular)),
            color = MaterialTheme.colorScheme.onSurface,
        ),
        maxLines = 1,
        autoSize = TextAutoSize.StepBased(minFontSize = 12.sp, maxFontSize = maxFontSize),
    )
}