package no.jitk.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class ImplementationTest {

    @Test
    fun `original test part 1`() {
        val lines = File("src/test/resources/test_data.txt").readLines()
        assertThat(processDay6Part1And2(lines = lines, days = 18)).isEqualTo(26)
    }

    @Test
    fun `actual run part 1`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()

        val millis = measureTimeMillis {
            println("Actual run part 1: " + processDay6Part1And2(lines = lines, days = 80))
        }
        println("part 1 took $millis millis to run")
    }

    @Test
    fun `original test part 2`() {
        val lines = File("src/test/resources/test_data.txt").readLines()
        assertThat(processDay6Part1And2(lines = lines, days = 256)).isEqualTo(26984457539)
    }

    @Test
    fun `actual run part 2`() {
        val lines = File("src/test/resources/actual_data.txt").readLines()
        val millis = measureTimeMillis {
            println("Actual run part 2: " + processDay6Part1And2(lines = lines, days = 256))
        }
        println("part 2 took $millis millis to run")
    }

}