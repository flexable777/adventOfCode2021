package no.jitk.advent.day5

fun processDay5Part1(data: List<String>): Int {

    val lines = data.map {
        val split = it.split(" -> ")
        val fromSplit = split[0].split(",")
        val from = Coordinate(
            x = fromSplit[0].toInt(),
            y = fromSplit[1].toInt()
        )
        val toSplit = split[1].split(",")
        val to = Coordinate(
            x = toSplit[0].toInt(),
            y = toSplit[1].toInt()
        )
        Line(from = to, to = from)
    }.filter {
        it.isStraight()
    }

    val uniqueCoordinatesHit = mutableSetOf<Coordinate>()

    val checkedPairs = mutableSetOf<Pair<Line, Line>>()

    for (oLine in lines) {
        for (iLine in lines) {
            if (oLine != iLine && !checkedPairs.contains(oLine to iLine)) {
                val coordinates = oLine.crossesWith(iLine)
                uniqueCoordinatesHit.addAll(coordinates)
            }
            checkedPairs.add(oLine to iLine)
            checkedPairs.add(iLine to oLine)
        }
    }

    return uniqueCoordinatesHit.size
}

fun processDay5Part2(data: List<String>): Int {
    val lines = data.map {
        val split = it.split(" -> ")
        val fromSplit = split[0].split(",")
        val from = Coordinate(
            x = fromSplit[0].toInt(),
            y = fromSplit[1].toInt()
        )
        val toSplit = split[1].split(",")
        val to = Coordinate(
            x = toSplit[0].toInt(),
            y = toSplit[1].toInt()
        )
        Line(from = from, to = to)
    }

    val uniqueCoordinatesHit = mutableSetOf<Coordinate>()

    val checkedPairs = mutableSetOf<Pair<Line, Line>>()

    for (oLine in lines) {
        for (iLine in lines) {
            if (oLine != iLine && !checkedPairs.contains(oLine to iLine)) {
                val coordinates = oLine.crossesWith(iLine)
                uniqueCoordinatesHit.addAll(coordinates)
            }
            checkedPairs.add(oLine to iLine)
            checkedPairs.add(iLine to oLine)
        }
    }

    return uniqueCoordinatesHit.size
}

data class Line(val from: Coordinate, val to: Coordinate) {
    private val coordinates: List<Coordinate>

    init {
        coordinates = getLineAsCoordinates()
    }

    fun isStraight() = from.x == to.x || from.y == to.y

    //TODO takes too much time. Refactor.
    fun crossesWith(otherLine: Line): List<Coordinate> {
        val foundCoordinates = mutableListOf<Coordinate>()
        coordinates.forEach { coordinate ->
            if (coordinate in otherLine.coordinates) {
                foundCoordinates += coordinate
            }
        }
        return foundCoordinates
    }

    //TODO don't need to do this upfront, but seems pretty fast.
    private fun getLineAsCoordinates(): List<Coordinate> {
        val coordinates = mutableListOf<Coordinate>()
        if (isStraight()) {
            return if (from.x == to.x) {
                if (from.y < to.y) {
                    from.y.rangeTo(to.y).forEach {
                        coordinates += Coordinate(x = from.x, y = it)
                    }
                } else {
                    to.y.rangeTo(from.y).forEach {
                        coordinates += Coordinate(x = from.x, y = it)
                    }
                }
                coordinates
            } else {
                if (from.x < to.x) {
                    from.x.rangeTo(to.x).forEach {
                        coordinates += Coordinate(x = it, y = from.y)
                    }
                } else {
                    to.x.rangeTo(from.x).forEach {
                        coordinates += Coordinate(x = it, y = from.y)
                    }
                }
                coordinates
            }
        } else {
            if (from.x > to.x && from.y > to.y) {
                var startX = from.x
                var startY = from.y
                repeat((from.x - to.x) + 1) {
                    coordinates += Coordinate(
                        x = startX--,
                        y = startY--
                    )
                }
            } else if (from.x > to.x && from.y < to.y) {
                var startX = from.x
                var startY = from.y
                repeat((from.x - to.x) + 1) {
                    coordinates += Coordinate(
                        x = startX--,
                        y = startY++
                    )
                }
            } else if (from.x < to.x && from.y < to.y) {
                var startX = from.x
                var startY = from.y
                repeat((to.x - from.x) + 1) {
                    coordinates += Coordinate(
                        x = startX++,
                        y = startY++
                    )
                }
            } else if (from.x < to.x && from.y > to.y) {
                var startX = from.x
                var startY = from.y
                repeat((to.x - from.x) + 1) {
                    coordinates += Coordinate(
                        x = startX++,
                        y = startY--
                    )
                }
            }
        }
        return coordinates
    }
}

data class Coordinate(val x: Int, val y: Int)
