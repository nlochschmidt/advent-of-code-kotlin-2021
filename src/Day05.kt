import Day05.Line.LineType.Diagonal
import Day05.Line.LineType.Horizontal
import Day05.Line.LineType.Singularity
import Day05.Line.LineType.Vertical
import java.lang.Integer.max
import kotlin.math.abs
import kotlin.math.sign

object Day05 {

    data class Point(val x: Int, val y: Int)
    data class Line(val from: Point, val to: Point) {
        val type: LineType get() {
            val sameX = from.x == to.x
            val sameY = from.y == to.y
            return when (sameX to sameY) {
                true to true -> Singularity
                true to false -> Vertical
                false to true -> Horizontal
                false to false -> Diagonal
                else -> throw IllegalStateException()
            }
        }

        val points: List<Point> get() {
            val diffX = to.x - from.x
            val diffY = to.y - from.y
            val xstep = diffX.sign
            val ystep = diffY.sign
            val longestDiff = max(abs(diffX), abs(diffY))
            return (0..longestDiff).map { diff ->
                Point(from.x + diff * xstep, from.y + diff * ystep)
            }
        }

        enum class LineType {
            Horizontal,
            Vertical,
            Diagonal,
            Singularity,
        }
    }


    fun parseLine(s: String): Line {
        val (fromX, fromY, toX, toY) = s.split(",", " -> ").map { it.toInt() }
        return Line(Point(fromX,fromY), Point(toX,toY))
    }

    fun part1(input: List<String>): Int {
        return input
            .map { parseLine(it) }
            .filter { it.type == Horizontal || it.type == Vertical }
            .flatMap { it.points }
            .groupBy { it }
            .count { (_, groupedPoints) -> groupedPoints.size > 1 }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { parseLine(it) }
            .flatMap { it.points }
            .groupBy { it }
            .count { (_, groupedPoints) -> groupedPoints.size > 1 }
    }

}

fun main() {
    println("Part 1: ${Day05.part1(readInput("Day05"))}")
    println("Part 2: ${Day05.part2(readInput("Day05"))}")
}