package app.trackching

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

private val StickerText = TextStyle(fontSize = 20.sp, lineHeight = 22.sp, fontWeight = FontWeight.ExtraBold)

/** Draws a solid copy of this box, shifted right and down: the "sticker" edge. */
fun Modifier.offsetShadow(color: Color, x: Dp = 6.dp, y: Dp = 5.dp): Modifier =
    drawBehind {
        drawRect(color = color, topLeft = Offset(x.toPx(), y.toPx()), size = size)
    }

/**
 * Section label. One line: a blue sticker with an orange edge.
 * Two lines (`second`): a blue sticker with an orange one tucked under it.
 */
@Composable
fun Tag(text: String, modifier: Modifier = Modifier, second: String? = null) {
    val c = TrackchingTheme.colors
    if (second == null) {
        Text(
            text.uppercase(),
            modifier = modifier
                .padding(end = 6.dp, bottom = 5.dp) // room for the edge
                .offsetShadow(c.orange)
                .background(c.blue)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            color = c.onBlue,
            style = StickerText,
            maxLines = 1,
        )
    } else {
        Column(modifier) {
            Text(
                text.uppercase(),
                modifier = Modifier
                    .zIndex(1f) // draw on top of the orange one
                    .background(c.blue)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                color = c.onBlue,
                style = StickerText,
                maxLines = 1,
            )
            Text(
                second.uppercase(),
                modifier = Modifier
                    .padding(start = 24.dp)
                    .offset(y = (-9).dp) // slide up under the blue one
                    .background(c.orange)
                    .padding(start = 12.dp, end = 12.dp, top = 13.dp, bottom = 4.dp),
                color = c.onOrange,
                style = StickerText,
                maxLines = 1,
            )
        }
    }
}

/** A solid block with an offset edge, used for big buttons and highlights. */
@Composable
fun OffsetBlock(
    modifier: Modifier = Modifier,
    color: Color = TrackchingTheme.colors.orange,
    edge: Color = TrackchingTheme.colors.blue,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier
            .padding(end = 6.dp, bottom = 5.dp)
            .offsetShadow(edge)
            .background(color),
        contentAlignment = Alignment.Center,
        content = content,
    )
}

/**
 * A share bar: orange fill over a light track, with a blue edge.
 * `fraction` is 0f to 1f. Put the amount (and anything else) in `content`.
 */
@Composable
fun Bar(
    fraction: Float,
    modifier: Modifier = Modifier,
    label: String? = null,
    height: Dp = 44.dp,
    content: @Composable RowScope.() -> Unit = {},
) {
    val c = TrackchingTheme.colors
    Column(modifier) {
        if (label != null) {
            Text(
                label,
                modifier = Modifier
                    .fillMaxWidth(0.66f)
                    .zIndex(1f)
                    .background(c.blue)
                    .padding(horizontal = 8.dp, vertical = 1.dp),
                color = c.onBlue,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Box(
            Modifier
                .padding(start = 6.dp, end = 7.dp, bottom = 6.dp)
                .fillMaxWidth()
                .height(height)
                .offsetShadow(c.blue, x = 7.dp, y = 6.dp)
                .background(c.track),
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction.coerceIn(0f, 1f))
                    .background(c.orange),
            )
            Row(
                Modifier.fillMaxSize().padding(horizontal = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}

/** A tappable filter or option. Blue when selected. */
@Composable
fun Chip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    dot: Color? = null,
) {
    val c = TrackchingTheme.colors
    Row(
        modifier
            .minimumInteractiveComponentSize() // at least 48 dp to tap
            .background(if (selected) c.blue else c.field)
            .selectable(selected = selected, onClick = onClick, role = Role.Checkbox)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (dot != null) Box(Modifier.size(8.dp).background(dot, CircleShape))
        Text(
            text,
            color = if (selected) c.onBlue else c.ink,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}