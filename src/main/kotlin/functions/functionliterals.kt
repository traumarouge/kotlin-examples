package functions

// Kotlin Programmer Dictionary: Function Type vs Function literal vs Lambda expression vs Anonymous function
// https://blog.kotlin-academy.com/kotlin-programmer-dictionary/home

// a function literal can be a lambda or an anonymous function
// argument types in lambda can be inferred from property type
val greet: () -> Unit = { println("Hello") }
val square: (Int) -> Int = { n -> n * n }
val producePrinter: () -> () -> Unit = { { println("printing") } }

// property type can be inferred from argument types in lambda
val greet2 = { println("Hello") }
val square2 = { n: Int -> n * n }
val producePrinter2 = { { println("printing") } }

// anonymous functions (here with inferred property types)
val greet3 = fun() { println("Hello") }
val square3 = fun(n: Int) = n * n
val producePrinter3 = fun() = fun() { println("printing") }

// a function type is just syntactic sugar for an interface,
// so it can be used as type argument or can be implemented
class MyFunction : () -> Unit {
    override fun invoke() {
        println("invoked")
    }
}


fun main(args: Array<String>) {
    greet()
    println(square(4))
    producePrinter()()

    greet2()
    println(square2(4))
    producePrinter2()()

    greet3()
    println(square3(4))
    producePrinter3()()

    val function = MyFunction()
    function()
}
