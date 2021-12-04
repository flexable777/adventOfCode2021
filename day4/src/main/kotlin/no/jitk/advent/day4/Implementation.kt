package no.jitk.advent.day4


fun processDay4Part1(lines: MutableList<String>): Int {
    val (draws, boards) = parseInput(lines)

    val drawn = mutableListOf<Int>()

    for (draw in draws) {
        drawn.add(draw)

        //exists in any board?
        if (drawn.size >= 5) {
            for ((i, board) in boards.withIndex()) {
                for (s in board.setOfPossibleRows) {
                    if (drawn.containsAll(s)) {
                        println("Bingo! Winner is board $i with numbers $s")
                        return board.setOfPossibleRows.flatten().toSet().filter { it !in drawn }.sum() * draw
                    }
                }
            }
        }
    }
    throw RuntimeException("something's wrong")
}

fun processDay4Part2(lines: MutableList<String>): Int {
    val (draws, boards) = parseInput(lines)

    val drawn = mutableListOf<Int>()

    for (draw in draws) {
        drawn.add(draw)

        //exists in any board?
        if (drawn.size >= 5) {
            for ((i, board) in boards.withIndex()) {
                for (row in board.setOfPossibleRows) {
                    if (drawn.containsAll(row) && draw in row) {
                        if (isOnlyOneBoardLeft(boards) && !board.hasWon) {
                            println("The last winner")
                            println("Winner is board $i with numbers $row and draw is $draw")
                            return board.setOfPossibleRows.flatten().toSet().filter { it !in drawn }.sum() * draw
                        }
                        board.hasWon = true
                    }
                }
            }
        }
    }
    throw RuntimeException("What?")
}

private fun isOnlyOneBoardLeft(boards: List<Board>) = boards.count { !it.hasWon } == 1

private fun parseInput(lines: MutableList<String>): Pair<List<Int>, List<Board>> {
    val draws = lines.first().split(",").map { it.toInt() }

    val sublist = lines.subList(2, lines.size)
    sublist.removeIf { it.isBlank() }

    //Boards with all the rows
    val boards = sublist.windowed(size = 5, step = 5).map { rowsOfStrings ->
        val board = Board()

        rowsOfStrings.forEach {
            board.setOfPossibleRows.add(it.replace("  ", " ").trim().split(" ").map { a -> a.toInt() })
        }
        board
    }

    //Add all the columns also
    boards.map { board ->
        for (i in 0 until 5) {
            val cols = mutableListOf<Int>()
            for (row in board.setOfPossibleRows.subList(0, 5)) {
                cols.add(row[i])
            }
            board.setOfPossibleRows.add(cols)
        }
    }
    return Pair(draws, boards)
}

data class Board(
    val setOfPossibleRows: MutableList<List<Int>> = mutableListOf(),
    var hasWon: Boolean = false
)