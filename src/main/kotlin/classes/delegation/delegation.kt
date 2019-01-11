package classes.delegation

// decorator pattern is natively supported, requiring zero boilerplate code

interface Cop {
    fun equip()
}


class NormalCop : Cop {
    override fun equip() {
        println("I put on my uniform")
    }
}

// compiler generated methods forward method calls to the decorated object
class LazyCop(private val cop: Cop) : Cop by cop {
    private val donutsEaten get() = (1..9).random()

    init {
        println("I've eaten $donutsEaten donuts")
    }
}

class AntiRiotCop(private val cop: Cop) : Cop by cop {
    override fun equip() {
        println("I put on my lovely riot gear")
    }
}

fun main() {
    LazyCop(NormalCop()).equip()
    AntiRiotCop(NormalCop()).equip()
}