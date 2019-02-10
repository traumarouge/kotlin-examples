package extensions.elevation

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class ElevationSmootingTest {

    @Test
    fun returnSameListForLessThanThreePoints() {
        val p1 = Point3D(8.00000, 49.00000, 100)
        val p2 = Point3D(8.00100, 49.00000, 100)

        val points = listOf(p1, p2)
        assertSame(points, points.smooth())
    }

    @Test
    fun smoothPointsNotExceedingWindowCapacity() {
        val p1 = Point3D(8.00000, 49.00000, 100) // the first point -> +1 to window
        val p2 = Point3D(8.00030, 49.00000, 120) // distance: 21.89 -> +2 to window
        val p3 = Point3D(8.00050, 49.00000, 130) // distance: 14.59 -> +1 to window
        val p4 = Point3D(8.00060, 49.00000, 125) // distance: 7.30 -> +1 to window
        val p5 = Point3D(8.00110, 49.00000, 100) // distance: 36.48 -> +3 to window

        val points = listOf(p1, p2, p3, p4, p5)
        val smoothedPoints = points.smooth()
        assertEquals(5, smoothedPoints.size)

        assertEquals(100, smoothedPoints[0].ele)
        assertEquals(110, smoothedPoints[1].ele)
        assertEquals(115, smoothedPoints[2].ele)
        assertEquals(117, smoothedPoints[3].ele)
        assertEquals(114, smoothedPoints[4].ele)
    }

    @Test
    fun smoothPointsExceedingWindowCapacity() {
        val p1 = Point3D(8.00000, 49.00000, 100) // the first point -> +1 to window
        val p2 = Point3D(8.00020, 49.00000, 900) // distance: 14.59 -> +1 to window
        val p3 = Point3D(8.00275, 49.00000, 900) // distance: 186.02 -> +18 to window

        // adding another point exceeds the maximum window size
        val p4 = Point3D(8.00295, 49.00000, 900) // distance: 186.02 -> +18 to window

        val points = listOf(p1, p2, p3, p4)
        val smoothedPoints = points.smooth()
        assertEquals(4, smoothedPoints.size)

        // first points elevation moved out of window,
        // thus not having any influence on 4th points elevation
        assertEquals(900, smoothedPoints[3].ele)
    }
}