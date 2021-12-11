package no.jitk.advent.day11

import no.jitk.advent.day11.Octopus.Coordinate

fun processDay11Part1(lines: List<String>, steps: Int = 100): Int {
    val octopusMatrix = lines.mapIndexed { y, line ->
        line.toList().mapIndexed { x, o -> Octopus(level = o.digitToInt(), coordinate = Coordinate(y, x)) }
    }
    val octopuses = octopusMatrix.flatten()

    //Store all neighbours for speed
    octopuses.forEach { octopus ->
        val neighbours = getAllNeighbours(octopus, octopusMatrix)
        octopus.neighbours = neighbours
    }

    repeat(steps) {
        octopuses.forEach { octopus ->
            octopus.increaseLevel()
            search(octopus, octopusMatrix)
        }
        octopuses.forEach { octopus ->
            if (octopus.hasFlashed) {
                octopus.reset()
            }
        }
    }

    return octopuses.sumOf { it.flashCount }
}

fun processDay11Part2(lines: List<String>): Int {
    val octopusMatrix = lines.mapIndexed { y, line ->
        line.toList().mapIndexed { x, o -> Octopus(level = o.digitToInt(), coordinate = Coordinate(y, x)) }
    }
    val octopuses = octopusMatrix.flatten()

    //Store all neighbours for speed
    octopuses.forEach { octopus ->
        val neighbours = getAllNeighbours(octopus, octopusMatrix)
        octopus.neighbours = neighbours
    }

    var counter = 0

    while (counter++ >= 0) {
        octopuses.forEach { octopus ->
            octopus.increaseLevel()
            search(octopus, octopusMatrix)
        }
        var zeroCounter = 0
        octopuses.forEach { octopus ->
            if (octopus.hasFlashed) {
                octopus.reset()
            }
            if (octopus.level == 0) {
                zeroCounter++
            }
        }
        if (zeroCounter == octopuses.size) {
            return counter
        }
    }

    throw RuntimeException("?")
}

fun search(octopus: Octopus, octopusMatrix: List<List<Octopus>>) {
    if (octopus.level > 9 && !octopus.hasFlashed) {
        octopus.flash()
        //update all neighbours that haven't already flashed
        octopus.neighbours!!.filter { !it.hasFlashed }.forEach { otherOctopus ->
            otherOctopus.increaseLevel()
            search(otherOctopus, octopusMatrix)
        }
    }
}

data class Octopus(
    var level: Int,
    var hasFlashed: Boolean = false,
    var flashCount: Int = 0,
    val coordinate: Coordinate,
    var neighbours: List<Octopus>? = null
) {

    fun increaseLevel() {
        level++
    }

    fun flash() {
        hasFlashed = true
        flashCount++
    }

    fun reset() {
        hasFlashed = false
        level = 0
    }

    data class Coordinate(val y: Int, val x: Int)
}

//TODO maybe simplify
fun getAllNeighbours(octopus: Octopus, intMatrix: List<List<Octopus>>): List<Octopus> {
    val result = mutableListOf<Octopus>()
    val (y, x) = octopus.coordinate
    if (y > 0) {
        result += intMatrix[y - 1][x]
    }
    if (x > 0) {
        result += intMatrix[y][x - 1]
    }
    if (y < intMatrix.size - 1) {
        result += intMatrix[y + 1][x]
    }
    if (x < intMatrix[0].size - 1) {
        result += intMatrix[y][x + 1]
    }
    if (y > 0 && x > 0) {
        result += intMatrix[y - 1][x - 1]
    }
    if (y < intMatrix.size - 1 && x > 0) {
        result += intMatrix[y + 1][x - 1]
    }
    if (y > 0 && x < intMatrix[0].size - 1) {
        result += intMatrix[y - 1][x + 1]
    }
    if (y < intMatrix.size - 1 && x < intMatrix[0].size - 1) {
        result += intMatrix[y + 1][x + 1]
    }
    return result
}
