package ranges.rainbow

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import ranges.rainbow.RainbowColor.*
import java.lang.IllegalArgumentException

class RainbowColorTest {

    @Test
    fun rangeIsEmpty() {
        assertTrue((VIOLET..RED).isEmpty())
        assertTrue((RED downTo VIOLET).isEmpty())
    }

    @Test
    fun stepMustBePositive() {
        assertThrows<IllegalArgumentException> { RED..VIOLET step 0 }
        assertThrows<IllegalArgumentException> { RED..VIOLET step -1 }
        assertThrows<IllegalArgumentException> { VIOLET downTo RED step 0 }
        assertThrows<IllegalArgumentException> { VIOLET downTo RED step -1 }
    }

    @Test
    fun iterateProgression() {
        val rainbowColors = arrayListOf<RainbowColor>()
        for (color in RED..VIOLET) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET), rainbowColors)

        rainbowColors.clear()
        for (color in ORANGE..INDIGO) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(ORANGE, YELLOW, GREEN, BLUE, INDIGO), rainbowColors)

        rainbowColors.clear()
        for (color in VIOLET..VIOLET) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(VIOLET), rainbowColors)

        rainbowColors.clear()
        for (color in RED..VIOLET step 2) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(RED, YELLOW, BLUE, VIOLET), rainbowColors)

        rainbowColors.clear()
        for (color in INDIGO..VIOLET step 2) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(INDIGO), rainbowColors)
    }

    @Test
    fun iterateDownToProgression() {
        val rainbowColors = arrayListOf<RainbowColor>()
        for (color in VIOLET downTo RED) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(VIOLET, INDIGO, BLUE, GREEN, YELLOW, ORANGE, RED), rainbowColors)

        rainbowColors.clear()
        for (color in INDIGO downTo ORANGE) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(INDIGO, BLUE, GREEN, YELLOW, ORANGE), rainbowColors)

        rainbowColors.clear()
        for (color in RED downTo RED) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(RED), rainbowColors)

        rainbowColors.clear()
        for (color in VIOLET downTo RED step 2) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(VIOLET, BLUE, YELLOW, RED), rainbowColors)

        rainbowColors.clear()
        for (color in ORANGE downTo RED step 2) {
            rainbowColors.add(color)
        }

        assertEquals(listOf(ORANGE), rainbowColors)
    }
}