package no.jitk.advent

class Implementation {

    fun processPart1(lines: List<Long>): Long {
        var counter = 0L
        lines.windowed(size = 2).forEach { w ->
            if (w.last() > w.first()) {
                counter++
            }
        }
        return counter
    }

    fun processPart2(lines: List<Long>): Long {
        var counter = 0L
        //Ignore first window
        var lastWindowSize = Long.MAX_VALUE

        lines.windowed(size = 3).forEach { w ->
            val currentWindowSize = w.sum()
            if (currentWindowSize > lastWindowSize) {
                counter++
            }
            lastWindowSize = currentWindowSize
        }

        return counter
    }
}