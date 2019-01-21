package http.httpclient

import com.jayway.jsonpath.Configuration
import com.jayway.jsonpath.JsonPath
import net.minidev.json.JSONArray
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


val url = """http://maps.foobar.net
    |/osrm/route/v1/undefined/8.630698,49.161429;8.598024,49.124118
    |?overview=full&geometries=geojson&annotations=nodes
    |""".trimMargin().replace(System.lineSeparator(), "")


data class Point(val lon: Double, val lat: Double)
data class OSRMPoint(val node: Long, val point: Point)

fun main(args: Array<String>) {
    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    println(response)

    val document = Configuration.defaultConfiguration().jsonProvider().parse(response.body())
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
