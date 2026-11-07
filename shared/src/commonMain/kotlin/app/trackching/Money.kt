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

/** What kind of transaction an amount belongs to. It decides the sign. */
enum class AmountKind { Expense, Income, Transfer }

/** 123200 -> "1,232.00". Never shows a sign. */
fun formatCentavos(centavos: Long): String {
    val abs = if (centavos < 0) -centavos else centavos
    val pesos = (abs / 100).toString()
        .reversed()          // "1232" -> "2321"
        .chunked(3)          // ["232", "1"]
        .joinToString(",")   // "232,1"
        .reversed()          // "1,232"
    val cents = (abs % 100).toString().padStart(2, '0')  // 5 -> "05"
    return "$pesos.$cents"
}

/** 123200 as an Expense -> "− ₱ 1,232.00". */
fun formatAmount(centavos: Long, kind: AmountKind): String {
    val sign = when (kind) {
        AmountKind.Expense -> "− "
        AmountKind.Income -> "+ "
        AmountKind.Transfer -> ""
    }
    return "$sign₱ ${formatCentavos(centavos)}"
}