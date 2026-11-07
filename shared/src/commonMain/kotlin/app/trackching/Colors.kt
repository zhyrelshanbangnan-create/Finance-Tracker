package app.trackching

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/** Trackching's own color names, taken from the prototype. */
@Immutable
data class TrackchingColors(
    val band: Color,      // cards and list boxes
    val band2: Color,     // screen background
    val sheet: Color,     // bottom sheets
    val field: Color,     // inputs and unselected chips
    val ink: Color,       // main text
    val ink2: Color,      // secondary text
    val line: Color,      // dividers
    val blue: Color,
    val orange: Color,
    val orangeInk: Color, // orange used AS TEXT; darker in light mode so it stays readable
    val track: Color,     // the empty part of a bar
    val income: Color,
    val expense: Color,
    val alert: Color,
    val onBlue: Color = Color(0xFFFFFFFF),   // text on blue
    val onOrange: Color = Color(0xFF111111), // text on orange or on a track
)

val DarkColors = TrackchingColors(
    band = Color(0xFF000000),
    band2 = Color(0xFF262626),
    sheet = Color(0xFF17181C),
    field = Color(0xFF24262C),
    ink = Color(0xFFFFFFFF),
    ink2 = Color(0xFFA9ADB7),
    line = Color(0xFF34363D),
    blue = Color(0xFF1663FF),
    orange = Color(0xFFFFAB00),
    orangeInk = Color(0xFFFFAB00),
    track = Color(0xFFFFFFFF),
    income = Color(0xFF3DDC6A),
    expense = Color(0xFFFF3B30),
    alert = Color(0xFFD42A20),
)

val LightColors = TrackchingColors(
    band = Color(0xFFFFFFFF),
    band2 = Color(0xFFE4E6EB),
    sheet = Color(0xFFFFFFFF),
    field = Color(0xFFF1F2F5),
    ink = Color(0xFF111216),
    ink2 = Color(0xFF5A5F6B),
    line = Color(0xFFD6D9E0),
    blue = Color(0xFF1663FF),
    orange = Color(0xFFFFAB00),
    orangeInk = Color(0xFF8A5200),
    track = Color(0xFFE3E6EC),
    income = Color(0xFF11873A),
    expense = Color(0xFFD42A20),
    alert = Color(0xFFD42A20),
)

val LocalTrackchingColors = staticCompositionLocalOf { DarkColors }