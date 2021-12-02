package no.jitk.advent

class Implementation {

    fun processPart1(commands: List<Pair<String, Int>>): Int {
        var horPos = 0
        var depth = 0

        commands.forEach { command ->
            when (command.first) {
                "forward" -> horPos += command.second
                "up" -> depth -= command.second
                "down" -> depth += command.second
            }
        }

        return horPos * depth
    }

    fun processPart2(commands: List<Pair<String, Int>>): Int {
        var horPos = 0
        var depth = 0
        var aim = 0

        commands.forEach { command ->
            when (command.first) {
                "forward" -> {
                    horPos += command.second
                    depth += aim * command.second
                }
                "up" -> aim -= command.second
                "down" -> aim += command.second
            }
        }

        return horPos * depth
    }
}