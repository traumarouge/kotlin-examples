package extensions

class Pointer(val name: String, val next: Pointer? = null)

// extension for nullable types: method calls on a null receiver are allowed without safe
// call operator. Inside the function body 'this' can be null
fun Pointer?.info(): String = this?.let { "$name -> ${this.next.info()}" } ?: "undefined"


fun main(args: Array<String>) {

    println(Pointer("first", Pointer("second")).info())

    val nullPointer: Pointer? = null
    println(nullPointer.info())
}