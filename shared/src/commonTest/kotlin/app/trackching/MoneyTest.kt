package app.trackching

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MoneyTest {

    @Test
    fun wholePesosBecomeCentavos() {
        assertEquals(32500L, parseCentavos("325"))
    }

    @Test
    fun oneOrTwoDecimalsWork() {
        assertEquals(32550L, parseCentavos("325.5"))
        assertEquals(32550L, parseCentavos("325.50"))
        assertEquals(50L, parseCentavos(".50"))
    }

    @Test
    fun badInputIsRejected() {
        assertNull(parseCentavos("abc"))
        assertNull(parseCentavos("10.505"))   // too many decimals
        assertNull(parseCentavos("1.2.3"))    // two dots
    }

    @Test
    fun formatShowsTwoDecimals() {
        assertEquals("325.00", formatCentavos(32500L))
        assertEquals("0.05", formatCentavos(5L))
    }

    @Test
    fun addsCentavosExactly() {
        assertEquals(30L, parseCentavos("0.10")!! + parseCentavos("0.20")!!)
    }

    @Test
    fun groupsThousands() {
        assertEquals("1,232.00", formatCentavos(123200))
        assertEquals("1,000,000.05", formatCentavos(100000005))
        assertEquals("0.00", formatCentavos(0))
    }

    @Test
    fun expenseUsesTrueMinusSign() {
        assertEquals("− ₱ 1,232.00", formatAmount(123200, AmountKind.Expense))
    }

    @Test
    fun incomeAndTransferSigns() {
        assertEquals("+ ₱ 1,232.00", formatAmount(123200, AmountKind.Income))
        assertEquals("₱ 1,232.00", formatAmount(123200, AmountKind.Transfer))
    }
}