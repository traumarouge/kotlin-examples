package extensions

import extensions.Direction.*


enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    companion object
}

fun Direction.Companion.from(c: Char): Direction {
    return when (c) {
        'N', 'n' -> NORTH
        'E', 'e' -> EAST
        'S', 's' -> SOUTH
        'W', 'w' -> WEST
        else -> throw IllegalArgumentException()
    }
}

fun Direction.opposite(): Direction = when (this) {
    NORTH -> SOUTH
    EAST -> WEST
    SOUTH -> NORTH
    WEST -> EAST
}


fun main(args: Array<String>) {
    println(Direction.from('n'))
    println(NORTH.opposite())
}