package app.trackching

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

/** Follows the phone's light/dark setting and hands out Trackching's colors. */
@Composable
fun TrackchingTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    val brand = if (dark) DarkColors else LightColors
    val base = if (dark) darkColorScheme() else lightColorScheme()

    // Map our colors onto Material's slots, so built-in parts
    // (NavigationBar, the + button, text fields) match without extra code.
    val material = base.copy(
        primary = brand.blue,
        onPrimary = brand.onBlue,
        primaryContainer = brand.blue,
        onPrimaryContainer = brand.onBlue,
        secondary = brand.orange,
        onSecondary = brand.onOrange,
        secondaryContainer = brand.blue,
        onSecondaryContainer = brand.onBlue,
        background = brand.band2,
        onBackground = brand.ink,
        surface = brand.band2,
        onSurface = brand.ink,
        onSurfaceVariant = brand.ink2,
        surfaceContainer = brand.band2,
        outline = brand.line,
        error = brand.alert,
    )

    CompositionLocalProvider(LocalTrackchingColors provides brand) {
        MaterialTheme(colorScheme = material, content = content)
    }
}

/** Read colors anywhere with `TrackchingTheme.colors.blue`. */
object TrackchingTheme {
    val colors: TrackchingColors
        @Composable @ReadOnlyComposable
        get() = LocalTrackchingColors.current
}