package lambdas.references

fun isOdd(n: Int) = n % 2 != 0

fun print(function: () -> Int) {
    println(function())
}

open class Person(val name: String, val age: Int)
class Developer(name: String, age: Int) : Person(name, age)

fun main(args: Array<String>) {
    // class reference: obtaining reference to a statically known class
    println(Person::class)

    // constructors: can be referenced just like methods and properties
    val createDeveloper = ::Developer
    val bob = createDeveloper("Bob the developer", 42)

    // bound class reference: reference to a class of a specific object
    println(bob::class)

    val alice = Person("Alice", 37)
    val people = listOf(alice, bob)

    // function and property reference
    people.map { p -> p.age }.filter(::isOdd)
    people.maxBy(Person::age)?.let { println(it.name) }

    // reference and bound reference
    val ageOf: (Person) -> Int = Person::age
    val bobsAge: () -> Int = bob::age

    // calling a reference directly...
    ageOf(bob)
    bobsAge()

    // ... or using it as function type value
    print(bobsAge)
}