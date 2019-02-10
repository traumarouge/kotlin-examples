package extensions.elevation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DistanceCalculationTest {

    @Test
    fun calculateOnEarthDistance() {
        val lat = 24.235
        val lon = 47.234

        assertEquals(15051, calculateDistance(lat, lon, lat - 0.1, lon + 0.1).toInt())
        assertEquals(15045, calculateDistance(lat, lon, lat + 0.1, lon - 0.1).toInt())
        assertEquals(150748, calculateDistance(lat, lon, lat - 1, lon + 1).toInt())
        assertEquals(150211, calculateDistance(lat, lon, lat + 1, lon - 1).toInt())
        assertEquals(1527919, calculateDistance(lat, lon, lat - 10, lon + 10).toInt())
        assertEquals(1474015, calculateDistance(lat, lon, lat + 10, lon - 10).toInt())
        assertEquals(1013735, calculateDistance(lat, lon, lat, lon - 10).toInt())
        assertEquals(1111949, calculateDistance(lat, lon, lat + 10, lon).toInt())
    }
}