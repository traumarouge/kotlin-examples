package functions

fun foo(vararg args: String) {
    println("foo(): $args")

    // args type is Array<out String> which makes it a read-only array
    bar(arrayOf(*args)) // copy args to new array of type Array<String>
    baz(args)
}

fun bar(args: Array<String>) {
    println("bar(): $args")
}

fun baz(args: Array<out String>) {
    println("baz(): $args")
}

fun main(args: Array<String>) {
    val array = arrayOf("one", "two")

    foo(*array, "three")  // spread operator
    bar(array)
    baz(array)
}