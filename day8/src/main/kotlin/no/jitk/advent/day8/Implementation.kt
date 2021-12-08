package no.jitk.advent.day8

fun processDay8Part1(lines: List<String>) =
    lines.sumOf { line ->
        line.split(" | ").last().split(" ")
            .map { it.length }.count { it in listOf(2, 4, 3, 7) }
    }

fun processDay8Part2(lines: List<String>): Int {
    var total = 0

    lines.forEach { line ->
        val map = mutableMapOf<Int, String>()
        val sides = line.split(" | ")

        //get known numbers
        sides[0].split(" ")
            .map { it to it.length }.filter { it.second in listOf(2, 4, 3, 7) }.forEach { lettersAndLength ->
                when (lettersAndLength.second) {
                    2 -> map += 1 to lettersAndLength.first.toSortedSet().joinToString(separator = "")
                    4 -> map += 4 to lettersAndLength.first.toSortedSet().joinToString(separator = "")
                    3 -> map += 7 to lettersAndLength.first.toSortedSet().joinToString(separator = "")
                    //Wait with this one until last
//                7 -> map += 8 to lettersAndLength.first.toSortedSet().joinToString(separator = "")
                }
            }

        val signalPatterns = sides[0].split(" ").map { it.toSortedSet().joinToString(separator = "") }

        //Might be a better order to search/eliminate but works
        for (s in signalPatterns) {
            if (s.length == 5 && s.toList().containsAll(map[1]!!.toList())) {
                map += 3 to s
                break
            }
        }

        val totalNow = map.flatMap { it.value.toList() }.toSortedSet().joinToString("")
        map += 9 to totalNow

        val allCharacters = signalPatterns.single { it.length == 7 }.toMutableList()

        allCharacters.removeAll(map[9]!!.toList().toSet())
        val missing = allCharacters.first()

        signalPatterns.single { s -> s.length == 5 && s.contains(missing) }.apply { map += 2 to this }
        signalPatterns.single { s -> s.length == 6 && s.toList().containsAll(map[1]!!.toList()) && s !in map.values }
            .apply { map += 0 to this }
        signalPatterns.single { s -> s.length == 5 && s !in map.values }.apply { map += 5 to this }
        signalPatterns.single { s -> s.length == 6 && s !in map.values }.apply { map += 6 to this }
        signalPatterns.single { s -> s.length == 7 }.apply { map += 8 to this }

        var numberAsString = ""

        sides.last().split(" ").map { it.toSortedSet().joinToString("") }.forEach { s ->
            numberAsString += map.entries.single { it.value == s }.key
        }

        total += numberAsString.toInt()
    }
    return total
}
