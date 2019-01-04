package classes

// sealed classes are useful for representing restricted class hierarchies

// they cannot be instantiated directly, can have abstract members and are
// not allowed to have non-private constructors
// subclasses must be declared in the same file as the sealed class itself
sealed class Cop {

    abstract val id: String

    abstract fun greet()

    class GoodCop(override val id: String) : Cop() {
        override fun greet() {
            println("How can I help you?")
        }

        fun ask() {
            // ...
        }
    }
}

// since Kotlin 1.1 subclasses may be declared outside of the sealed class
// and they may be declared as data classes
data class BadCop(override val id: String) : Cop() {
    override fun greet() {
        println("Move on!")
    }

    fun bribe() {
        // ...
    }
}

fun meet(cop: Cop) = when (cop) {
    is Cop.GoodCop -> cop.ask()
    is BadCop -> cop.bribe()
}

fun main(args: Array<String>) {
    meet(Cop.GoodCop("gc-100"))
    meet(BadCop("bc-101"))
}