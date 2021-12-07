package no.jitk.day7

import kotlin.math.absoluteValue

fun processDay7Part1(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }

    val maxValue = positions.maxOrNull()!!
    var min = Int.MAX_VALUE

    for (i in 0 until maxValue) {
        var cost = 0
        positions.forEach { pos ->
            val absoluteValue = (pos - i).absoluteValue
            cost += absoluteValue
        }
        if (cost < min) {
            min = cost
        }
    }
    return min
}

fun processDay7Part2(lines: List<String>): Int {
    val positions = lines.first().split(",").map { it.toInt() }
    val maxValue = positions.maxOrNull()!!
    var min = Int.MAX_VALUE
    for (i in 0 until maxValue) {
        var cost = 0
        positions.forEach { pos ->
            cost += if (i < pos) {
                moveCrab(i, pos, 0, 1)
            } else {
                moveCrab(pos, i, 0, 1)
            }
        }
        if (cost < min) {
            min = cost
        }
    }

    return min
}

fun moveCrab(pos: Int, end: Int, cost: Int, step: Int): Int {
    if (pos == end) {
        return cost
    }
    return moveCrab(pos + 1, end, cost + step, step + 1)
}