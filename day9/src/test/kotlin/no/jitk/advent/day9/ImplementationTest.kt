package no.jitk.advent.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines()
        assertThat(processDay9Part1(lines)).isEqualTo(15)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + processDay9Part1(lines))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val lines = File("src/test/resources/test_data.txt").readLines()

        assertThat(processDay9Part2(lines)).isEqualTo(1134)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 2: " + processDay9Part2(lines))
        }
        println("part 2 took $millis millis to run")

    }

}