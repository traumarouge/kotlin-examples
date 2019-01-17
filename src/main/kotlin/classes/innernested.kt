package classes

open class Base {
    open fun print() {
        println("Base")
    }
}

class Enclosing(val value: Int, val unit: String) : Base() {

    // private members of nested/inner classes cannot be accessed by enclosing class
    class Nested(private val value: Int) {

        // non-inner class cannot access the enclosing classes members
        fun foo() {
            println("Nested: $value")
        }
    }

    // use qualified this/super in order to denote the receiver from an outer scope
    inner class Inner(private val value: Int) {
        fun bar() {
            print()
            this@Enclosing.print()
            super@Enclosing.print()
        }

        fun print() {
            // access member "unit" from enclosing class
            println("Inner: $value$unit")
        }
    }

    override fun print() {
        println("Enclosing: $value$unit")
    }
}

fun main() {
    Enclosing.Nested(2).foo()

    // constructor of inner class can be called only with receiver of containing class
    Enclosing(5, "m").Inner(3).bar()
}