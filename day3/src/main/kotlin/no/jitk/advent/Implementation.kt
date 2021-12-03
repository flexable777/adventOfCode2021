package no.jitk.advent

class Implementation3 {

    fun processPart1(lines: List<String>): Int {
        var gamma = ""

        for (i in lines[0].indices) {
            gamma += if (lines.map { it[i] }.count { it == '1' } > lines.size / 2) {
                "1"
            } else {
                "0"
            }
        }
        //TODO Should be able to inverse the bits for epsilon rate
        return gamma.toInt(2) * gamma
            .replace("1", "x")
            .replace("0", "1")
            .replace("x", "0")
            .toInt(2)
    }

    fun processPart2(lines: List<String>): Long {
        val oxygenRating = getRating(lines)
        val coScrubberRating = getRating(lines, wantsOxygenRating = false)
        return coScrubberRating.toLong(2) * oxygenRating.toLong(2)
    }

    private fun getRating(
        lines: List<String>,
        wantsOxygenRating: Boolean = true
    ): String {
        val remainingLines = lines.toMutableList()
        for (i in lines[0].indices) {
            if (remainingLines.size == 1) {
                return remainingLines.first()
            }
            if (remainingLines.size / 2 >= remainingLines.count { it[i] == '0' }) {
                remainingLines.removeIf { it[i] == if (wantsOxygenRating) '0' else '1' }
            } else {
                remainingLines.removeIf { it[i] == if (wantsOxygenRating) '1' else '0' }
            }
        }
        throw RuntimeException("How can this be?")
    }
}