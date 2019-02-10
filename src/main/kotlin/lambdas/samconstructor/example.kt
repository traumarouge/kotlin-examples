package lambdas.samconstructor

fun run(action: Runnable) {
    println(action)
    action.run()
}

@Suppress("ObjectLiteralToLambda")
fun main(args: Array<String>) {
    val sb = StringBuilder()

    // object expression as implementation of functional interface
    for (i in 1..2) {
        run(object : Runnable { // passes new Runnable instance on each invocation
            override fun run() {
                sb.append("*")
            }
        })
    }

    // object expression as implementation of functional interface
    var r: Runnable = object : Runnable {
        override fun run() {
            sb.append("*")
        }
    }

    for (i in 1..2) run(r) // passes same Runnable instance

    for (i in 1..2) {
        // SAM constructor: explicit conversion of lambda to functional interface
        run(Runnable { sb.append("*") }) // passes same Runnable instance
    }

    for (i in 1..2) {
        // SAM constructor: explicit conversion of lambda to functional interface
        // new Runnable instance as a variable is captured from surrounding scope
        run(Runnable { sb.append("*") })
    }

    // SAM constructor: explicit conversion of lambda to functional interface
    r = Runnable { sb.append("*") }
    for (i in 1..2) run(r) // passes same Runnable instance

    println(sb.toString())
}