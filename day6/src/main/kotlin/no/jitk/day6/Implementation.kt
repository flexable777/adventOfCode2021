package no.jitk.day6


fun processDay6Part1And2(lines: List<String>, days: Int = -1): Long {
    val fish = lines.first().split(",").map {
        it.toInt()
    }.toMutableList()

    var counter = 0L
    val memo = mutableMapOf<Pair<Int, Int>, Long>()

    for (f in fish) {
        counter += countFish(f, days, memo)
    }

    return counter
}

fun countFish(age: Int, days: Int, memo: MutableMap<Pair<Int, Int>, Long>): Long {
    if (memo.containsKey(age to days)) {
        return memo[age to days] ?: throw RuntimeException("?")
    }

    var counter = 1L
    var currentAge = age

    for (day in 0 until days) {
        if (currentAge == 0) {
            currentAge = 6
            counter += countFish(8, days - day - 1, memo)
        } else {
            currentAge--
        }
    }
    memo[age to days] = counter
    return counter
}