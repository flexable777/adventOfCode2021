package no.jitk.advent.day9

fun processDay9Part1(lines: List<String>): Int {
    val intMatrix = lines.map { it.toList().map { it.digitToInt() } }

    return intMatrix.flatMapIndexed { y, list ->
        list.mapIndexed { x, value ->
            value to getAllNeighbours(Coordinate(y, x), intMatrix)
        }
    }.sumOf { if (it.first < it.second.minOf { it.second }) it.first + 1 else 0 }
}

fun processDay9Part2(lines: List<String>): Int {
    val intMatrix = lines.map { it.toList().map { it.digitToInt() } }
    val basins = mutableListOf<Int>()
    intMatrix.forEachIndexed { y, list ->
        list.forEachIndexed { x, value ->
            val n = getAllNeighbours(Coordinate(y, x), intMatrix)
            if (value < n.minOf { it.second }) {
                val placesVisited = mutableSetOf<Coordinate>()
                search(Coordinate(y, x), intMatrix, placesVisited)
                basins += placesVisited.size
            }
        }
    }

    val sortedDescending = basins.sortedDescending()
    return sortedDescending[0] * sortedDescending[1] * sortedDescending[2]
}

fun search(coordinate: Coordinate, intMatrix: List<List<Int>>, placesVisited: MutableSet<Coordinate>) {
    getAllNeighbours(coordinate, intMatrix).filter { it.second != 9 && it.first !in placesVisited }.forEach {
        placesVisited += it.first
        search(it.first, intMatrix, placesVisited)
    }
}

fun getAllNeighbours(coordinate: Coordinate, intMatrix: List<List<Int>>): List<Pair<Coordinate, Int>> {
    val result = mutableListOf<Pair<Coordinate, Int>>()
    val (y, x) = coordinate
    if (y > 0) {
        result += Coordinate(y - 1, x) to intMatrix[y - 1][x]
    }
    if (x > 0) {
        result += Coordinate(y, x - 1) to intMatrix[y][x - 1]
    }
    if (y < intMatrix.size - 1) {
        result += Coordinate(y + 1, x) to intMatrix[y + 1][x]
    }
    if (x < intMatrix[0].size - 1) {
        result += Coordinate(y, x + 1) to intMatrix[y][x + 1]
    }
    return result
}

data class Coordinate(val y: Int, val x: Int)