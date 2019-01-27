package lambdas.receiver

fun encloseInXMLTag(sb: StringBuilder, tag: String, block: StringBuilder.() -> Unit): String {
    // the functions last parameter is an extension function of type StringBuilder

    sb.append("<$tag>")
    sb.block() // also accepted by compiler: block(sb)
    sb.append("</$tag>")

    return sb.toString()
}


fun main(args: Array<String>) {
    // lambda definition site: reference the receiver object by using "this" or omit it at all
    println(encloseInXMLTag(StringBuilder(), "foo") { this.append("content") })
    println(encloseInXMLTag(StringBuilder(), "bar") { append("content") })
}