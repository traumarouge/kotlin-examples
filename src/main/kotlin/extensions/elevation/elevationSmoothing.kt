package extensions.elevation

import java.util.*
import kotlin.math.ceil
import kotlin.math.round

const val WINDOW_SIZE = 20
const val MIN_DISTANCE = 10

data class Point3D(val lon: Double, val lat: Double, val ele: Int)

fun List<Point3D>.smooth(): List<Point3D> { // extension function
    if (size <= 2) return this

    val firstPoint = this.first()
    val smoothedPoints = mutableListOf(firstPoint)
    var eleSum = firstPoint.ele.toDouble()
    val values = LinkedList<Double>()
    values.add(eleSum)

    var (x0, y0, z0) = firstPoint
    forEachIndexed { index, point ->
        if (index == 0) return@forEachIndexed // ignore the first element in the point list

        val (x1, y1, z1) = point
        val distance: Double = calculateDistance(y0, x0, y1, x1)

        if (distance > MIN_DISTANCE) {
            val n: Int = ceil(distance / MIN_DISTANCE).toInt()
            for (j in 1 until n) {
                val ele: Double = z0 + j * (z1 - z0) / (n - 1.0)
                if (values.size == WINDOW_SIZE) {
                    eleSum -= values.first()
                    values.removeFirst()
                }

                eleSum += ele
                values.addLast(ele)
            }
        } else {
            if (values.size == WINDOW_SIZE) {
                eleSum -= values.first()
                values.removeFirst()
            }

            eleSum += z1
            values.addLast(z1.toDouble())
        }

        val eleAverage = round(eleSum / values.size).toInt()
        smoothedPoints.add(point.copy(ele = eleAverage))
        x0 = x1; y0 = y1; z0 = z1 // destructuring assignment not (yet) supported in Kotlin
    }

    return smoothedPoints
}
