package extensions.elevation

import java.lang.Math.toRadians
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

const val EARTH_RADIUS = 6371000 // meter

/**
 * Calculates the distance in meter between the given coordinate pairs.
 * See http://en.wikipedia.org/wiki/Haversine_formula
 */
fun calculateDistance(fromLat: Double, fromLon: Double, toLat: Double, toLon: Double): Double {

    fun normalizedDistance(): Double {
        val sinDeltaLat = sin(toRadians(toLat - fromLat) / 2)
        val sinDeltaLon = sin(toRadians(toLon - fromLon) / 2)

        return (sinDeltaLat * sinDeltaLat) +
                sinDeltaLon * sinDeltaLon * cos(toRadians(fromLat)) * cos(toRadians(toLat))
    }

    return EARTH_RADIUS * 2 * asin(sqrt(normalizedDistance()))
}
