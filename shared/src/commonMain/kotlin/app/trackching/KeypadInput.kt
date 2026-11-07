package app.trackching

/** Everything the amount keypad can send. */
sealed interface Key {
    data class Digits(val text: String) : Key // "0" to "9", or "00"
    data object Dot : Key
    data object Back : Key
    data object Clear : Key
}

/**
 * Returns the typed amount after one key press, e.g. "12.5" + Digits("0") = "12.50".
 * Limits: 9 digits before the dot (₱ 999,999,999) and 2 after it.
 * The result goes straight into parseCentavos().
 */
fun press(amount: String, key: Key): String = when (key) {
    Key.Back -> amount.dropLast(1)
    Key.Clear -> ""
    Key.Dot -> if ('.' in amount) amount else amount.ifEmpty { "0" } + "."
    is Key.Digits -> {
        val dot = amount.indexOf('.')
        when {
            dot >= 0 && amount.length - dot - 1 + key.text.length > 2 -> amount
            dot < 0 && (amount.trimStart('0') + key.text).length > 9 -> amount
            else -> {
                val next = (if (amount == "0") "" else amount) + key.text
                if (next == "00") "0" else next
            }
        }
    }
}