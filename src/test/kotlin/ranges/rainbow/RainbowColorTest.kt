package ranges.rainbow

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.*
import org.junit.jupiter.params.provider.MethodSource

import ranges.rainbow.RainbowColor.*
import java.lang.IllegalArgumentException
import java.util.stream.Stream

class RainbowColorTest {

    @Test
    fun closedRangeIsEmpty() {
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

    @ParameterizedTest
    @MethodSource("iterateProgressionArgumentsProvider")
    fun iterateProgression(progression: RainbowColorProgression, expectedRainbowColors: List<RainbowColor>) {
        val rainbowColors = arrayListOf<RainbowColor>()

        for (color in progression) {
            rainbowColors.add(color)
        }

        assertEquals(expectedRainbowColors, rainbowColors)
    }

    companion object {
        @JvmStatic
        fun iterateProgressionArgumentsProvider() = Stream.of(
                arguments(RED..VIOLET, listOf(RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET)),
                arguments(RED..VIOLET step 2, listOf(RED, YELLOW, BLUE, VIOLET)),
                arguments(ORANGE..YELLOW, listOf(ORANGE, YELLOW)),
                arguments(INDIGO..VIOLET step 2, listOf(INDIGO)),
                arguments(VIOLET..VIOLET, listOf(VIOLET)),

                arguments(VIOLET downTo RED, listOf(VIOLET, INDIGO, BLUE, GREEN, YELLOW, ORANGE, RED)),
                arguments(VIOLET downTo RED step 2, listOf(VIOLET, BLUE, YELLOW, RED)),
                arguments(YELLOW downTo ORANGE, listOf(YELLOW, ORANGE)),
                arguments(ORANGE downTo RED step 2, listOf(ORANGE)),
                arguments(RED downTo RED, listOf(RED))
        )
    }
}