package ranges.rainbow

enum class RainbowColor {

    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    operator fun rangeTo(other: RainbowColor) = RainbowColorProgression(this, other)

    infix fun downTo(other: RainbowColor) = RainbowColorProgression(other, this, -1)
}