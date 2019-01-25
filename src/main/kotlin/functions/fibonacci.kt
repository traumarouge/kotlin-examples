package functions

import kotlin.system.measureTimeMillis


fun fibRecursive(n: Long): Long =
        if (n <= 1) n else fibRecursive(n - 2) + fibRecursive(n - 1)

fun fibIterative(n: Long): Long {
    var l = 1L
    var p = 1L

    for (i in 3..n) {
        val tmp = l
        l += p
        p = tmp
    }

    return l
}

tailrec fun fibTailRec(n: Long, p: Long = 1, l: Long = 1): Long =
        if (n <= 2) l else fibTailRec(n - 1, l, l + p)


fun main(args: Array<String>) {
    println("recursive: ${measureTimeMillis { fibRecursive(42) }} ms")
    println("iterative: ${measureTimeMillis { fibIterative(42) }} ms")
    println("tail rec: ${measureTimeMillis { fibTailRec(42) }} ms")
}