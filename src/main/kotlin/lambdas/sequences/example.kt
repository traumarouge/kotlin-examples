package lambdas.sequences

fun main() {

    // sequenceOf
    println(sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9).filter { it % 2 == 0 }.toList())

    // generateSequence (here: 'take' restricts the sequence to the first 9 elements)
    println(generateSequence(1) { it + 1 }.take(9).filter { it % 2 == 0 }.toList())

    // asSequence (here: called on a range)
    println((1..9).asSequence().filter { it % 2 == 0 }.toList())

    // a sequence can be reused and evaluated multiple times
    val sequence = sequenceOf(1, 2, 3, 4, 5)
    println(sequence.reduce { sum, element -> sum * element })
    println(sequence.average())
}