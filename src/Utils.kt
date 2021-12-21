import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Parse comma separated line of numbers to Ints
 */
fun String.parseInts() = this.split(",").map(String::toInt)

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Create a new set of source files for the next day
 */
fun initNextDay() {
    val srcFolder = File("src")
    val lastDay = srcFolder
        .listFiles { _, name -> name.startsWith("Day") && name.endsWith(".kt") }
        .map { it.nameWithoutExtension.takeLast(2) }
        .maxOf { it.toInt() }
    val dayPrefix = "Day${lastDay.inc().toString().padStart(2, '0')}"
    File(srcFolder, "$dayPrefix.kt").also { newSourceFile ->
        newSourceFile.createNewFile()
        newSourceFile.writeText(
            """
            object $dayPrefix {
                fun part1(input: List<String>): Int {
                    return TODO()
                }
            
                fun part2(input: List<String>): Int {
                    return TODO()
                }
            }
            
            fun main() {
                println("Part 1: ${'$'}{${dayPrefix}.part1(readInput("$dayPrefix"))}")
                println("Part 2: ${'$'}{${dayPrefix}.part2(readInput("$dayPrefix"))}")
            }
        """.trimIndent()
        )
    }
    File(srcFolder, "$dayPrefix.txt").createNewFile()
    File(srcFolder, "${dayPrefix}_test.txt").createNewFile()
    File(File("test"), "${dayPrefix}Test.kt").also { newTestFile ->
        newTestFile.createNewFile()
        newTestFile.writeText(
            """
            import io.kotest.core.spec.style.FunSpec
            import io.kotest.matchers.shouldBe

            class ${dayPrefix}Test : FunSpec({

                context("part1") {
                    test("should pass acceptance test") {
                        ${dayPrefix}.part1(readInput("${dayPrefix}_test")) shouldBe -1
                    }
                }

                context("part2") {
                    test("should pass acceptance test") {
                        ${dayPrefix}.part2(readInput("${dayPrefix}_test")) shouldBe -1
                    }
                }
            })
            """.trimIndent()
        )
    }
}

fun main() {
    initNextDay()
}
