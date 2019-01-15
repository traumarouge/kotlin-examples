package classes

// an object declaration creates a singleton
object Singleton {
    fun print(s: String) {
        println(s)
    }
}

// object declarations are like regular classes, i.e. they can have properties, extend from classes
// and interfaces, etc. but they are not allowed to have constructors
data class Person(val name: String) {
    object PersonComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person) = p1.name.compareTo(p2.name, ignoreCase = true)
    }
}


fun main(args: Array<String>) {
    // refer to singleton object by its name
    Singleton.print("printed by a singleton")

    println(Person.PersonComparator.compare(Person("alice"), Person("Alice")) == 0)

    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.PersonComparator))
}