package classes.companion

// factory methods implemented using a companion object
class Cop private constructor(val isGoodCop: Boolean) {

    companion object Factory { // named companion object (if name is omitted, "Companion" is used)

        fun createGoodCop() = Cop(true)

        fun createBadCop() = Cop(false)
    }
}

fun main(args: Array<String>) {
    val goodCop = Cop.createGoodCop()
    println(goodCop.isGoodCop)

    val factory = Cop.Factory
    val badCop = factory.createBadCop()
    println(badCop.isGoodCop)

    // the name of the class used by itself acts as a reference to the companion object
    println(Cop === Cop.Factory)
}