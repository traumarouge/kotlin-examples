package http.httpurlconnection

import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import net.minidev.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


val url = """http://maps.foobar.net
    |/osrm/route/v1/undefined/8.630698,49.161429;8.598024,49.124118
    |?overview=full&geometries=geojson&annotations=nodes
    |""".trimMargin().replace(System.lineSeparator(), "")


data class Point(val lon: Double, val lat: Double)
data class OSRMPoint(val node: Long, val point: Point)

fun main(args: Array<String>) {
    val connection = URL(url).openConnection() as HttpURLConnection
    val content = connection.inputStream.bufferedReader().use { reader -> reader.readText() }

    println(content)

    val document = Configuration.defaultConfiguration().jsonProvider().parse(content)
    val annotations = JsonPath.read<List<Long>>(document, "$.routes[0].legs[0].annotation.nodes")
    val coordinates = JsonPath.read<List<JSONArray>>(document, "$.routes[0].geometry.coordinates")

    if (coordinates.size != annotations.size) throw IllegalStateException()

    val osrmPoints = (0 until annotations.size).map { i ->
        OSRMPoint(annotations[i], Point(coordinates[i][0] as Double, coordinates[i][1] as Double))
    }.toList()

    println("Summary: there are ${osrmPoints.size} OSRM points")
    println("First point: ${osrmPoints.first()}")
    println("Last point: ${osrmPoints.last()}")
}
