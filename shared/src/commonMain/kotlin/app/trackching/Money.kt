package app.trackching

/**
 * Turns what the user typed ("325", "325.5", "325.00") into centavos (32500).
 * Returns null if the text isn't a valid amount.
 */

fun parseCentavos(text: String): Long? {
    val parts = text.trim().split(".")
    if (parts.size > 2) return null

    val pesos = parts[0].ifEmpty{"0"}.toLongOrNull() ?: return null
    val decimals = parts.getOrElse(1){""}
    if (decimals.length > 2) return null
    val centavos = decimals.padEnd(2, '0').toLongOrNull() ?: return null

    return pesos * 100 + centavos
}

/** Turns centavos (32500) back into text ("325.00"). */
fun formatCentavos(centavos: Long): String {
    val pesos = centavos / 100
    val rest = (centavos % 100).toString().padStart(2, '0')
    return "$pesos.$rest"
}