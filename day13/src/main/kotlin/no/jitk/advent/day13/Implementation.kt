package no.jitk.advent.day13

fun processDay13Part1(lines: List<String>): Int {
    val coordinates = lines.filter { it.isNotBlank() && it[0].isDigit() }.map {
        val split = it.split(",")
        Coordinate(x = split[0].toInt(), y = split[1].toInt())
    }

    val maxX = coordinates.maxOf { it.x }
    val maxY = coordinates.maxOf { it.y }

    val matrix = mutableListOf<MutableList<Boolean>>()

    for (y in 0..maxY) {
        val row = mutableListOf<Boolean>()
        for (x in 0..maxX) {
            row += Coordinate(x, y) in coordinates
        }
        matrix += row
    }

//    println("Before folding:")
//    printMatrix(matrix)

    var currentMatrix = matrix.toMutableList()
    lines.filter { it.startsWith("fold") }.forEach { l ->
        val (axis, index) = l.split("fold along ")[1]
            .split("=")
        currentMatrix = if (axis == "y") {
            foldUp(currentMatrix, index.toInt())
        } else {
            foldLeft(currentMatrix, index.toInt())
        }
    }

    println("After folds:")
    printMatrix(currentMatrix)

    return currentMatrix.flatten().count { it }
}

fun foldLeft(matrix: MutableList<MutableList<Boolean>>, index: Int): MutableList<MutableList<Boolean>> {
    val reversedMatrix = matrix.map { it.subList(index, it.size).reversed() }
    val newMatrix = matrix.map { it.subList(0, index) }.toMutableList()

    for (y in 0 until newMatrix.size) {
        for (x in 0 until newMatrix[y].size) {
            if (reversedMatrix[y][x]) {
                newMatrix[y][x] = true
            }
        }
    }
    return newMatrix
}

fun foldUp(matrix: MutableList<MutableList<Boolean>>, index: Int): MutableList<MutableList<Boolean>> {
    val reversedMatrix = matrix.subList(index + 1, matrix.size).reversed()
    val newMatrix = matrix.subList(0, index)

    for (y in 0 until newMatrix.size) {
        for (x in 0 until newMatrix[y].size) {
            val otherY = y + (newMatrix.size - reversedMatrix.size)
            if (otherY >= 0) {
                if (reversedMatrix[otherY][x]) {
                    newMatrix[y][x] = true
                }
            }
        }
    }

    return newMatrix
}

private fun printMatrix(
    matrix: MutableList<MutableList<Boolean>>
) {
    for (y in matrix.indices) {
        for (x in matrix[y].indices) {
            if (matrix[y][x]) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}

data class Coordinate(val x: Int, val y: Int)