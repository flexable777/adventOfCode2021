package no.jitk.advent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data_part1.txt").readLines()

        assertThat(Implementation().processPart1(lines)).isEqualTo(0)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/test_data_part1.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + Implementation().processPart1(lines))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val lines = File("src/test/resources/actual_data_part2.txt").readLines()

        assertThat(Implementation().processPart2(lines)).isEqualTo(0)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data_part2.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 2: " + Implementation().processPart2(lines))
        }
        println("part 2 took $millis millis to run")

    }

}