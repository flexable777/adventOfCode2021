package no.jitk.advent.day17

import kotlin.math.absoluteValue

fun processDay17Part1(lines: List<String>): Int {
    val maxY = -10
//    val maxY = -108
    val m = (maxY.absoluteValue - 1)
    return m * (m + 1) / 2
}

fun processDay17Part2(lines: List<String>): Int {
    var xVelocity = 1

    val hits = mutableSetOf<Pair<Int, Int>>()

    repeat(500) {
        xVelocity++
        var yVelocity = -800
        repeat(1600) {
            val vValueX = xVelocity
            val vValueY = yVelocity++

            var dx = vValueX
            var dy = vValueY

            var x = 0
            var y = 0
            repeat(500) {
                if (isHit(x, y)) {
                    hits += vValueX to vValueY
                }
                if (dx > 0) {
                    x += dx--
                }
                y += dy--
            }
        }
    }
    return hits.size
}

fun isHit(x: Int, y: Int): Boolean {
//    val xRange = 20..30
//    val yRange = -10..-5

    val xRange = 169..206
    val yRange = -108..-68

    return x in xRange && y in yRange
}