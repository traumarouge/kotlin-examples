package ranges.rainbow

import java.lang.IllegalArgumentException

class RainbowColorProgression(override val start: RainbowColor, override val endInclusive: RainbowColor,
                              val step: Int = 1) : Iterable<RainbowColor>, ClosedRange<RainbowColor> {

    override fun iterator(): Iterator<RainbowColor> = RainbowColorIterator(start, endInclusive, step)

    infix fun step(n: Int): RainbowColorProgression = when {
        n <= 0 -> throw IllegalArgumentException("Step must be positive, was: $n")
        step < 0 -> RainbowColorProgression(start, endInclusive, -n)
        else -> RainbowColorProgression(start, endInclusive, n)
    }


    private class RainbowColorIterator(private var first: RainbowColor, private var last: RainbowColor,
                                       private var step: Int) : Iterator<RainbowColor> {

        private var next = if (step < 0) last else first
        private var done = false

        override fun hasNext() = !done && if (step < 0) next >= first else next <= last

        override fun next(): RainbowColor {
            done = next.ordinal + step > last.ordinal || next.ordinal + step < first.ordinal

            return if (done) next else next.also { next = RainbowColor.values()[next.ordinal + step] }
        }
    }
}