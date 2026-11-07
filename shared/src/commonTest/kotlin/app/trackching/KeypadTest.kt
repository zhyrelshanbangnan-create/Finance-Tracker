package app.trackching

import kotlin.test.Test
import kotlin.test.assertEquals

class KeypadTest {

    private fun type(vararg keys: Key): String = keys.fold("") { amount, key -> press(amount, key) }
    private fun d(text: String) = Key.Digits(text)

    @Test
    fun digitsAppend() {
        assertEquals("125", type(d("1"), d("2"), d("5")))
    }

    @Test
    fun dotOnEmptyStartsWithZero() {
        assertEquals("0.", type(Key.Dot))
    }

    @Test
    fun onlyOneDot() {
        assertEquals("1.5", type(d("1"), Key.Dot, d("5"), Key.Dot))
    }

    @Test
    fun atMostTwoDecimals() {
        assertEquals("1.25", type(d("1"), Key.Dot, d("2"), d("5"), d("9")))
        assertEquals("1.5", type(d("1"), Key.Dot, d("5"), d("00")))
    }

    @Test
    fun noLeadingZeros() {
        assertEquals("7", type(d("0"), d("00"), d("7")))
    }

    @Test
    fun atMostNineWholeDigits() {
        assertEquals("999999999", type(*Array(10) { d("9") }))
    }

    @Test
    fun backAndClear() {
        assertEquals("1", type(d("1"), d("2"), Key.Back))
        assertEquals("", type(d("1"), d("2"), Key.Clear))
    }

    @Test
    fun resultParsesToCentavos() {
        assertEquals(1250L, parseCentavos(type(d("1"), d("2"), Key.Dot, d("5"))))
    }
}