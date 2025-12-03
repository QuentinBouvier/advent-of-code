package commons

import java.io.File
import java.nio.file.Paths

fun inputsFileToLineSequence(year: Int, day: Int) = File("${Paths.get("").toAbsolutePath()}/src/main/kotlin/y$year/day$day/input.txt").bufferedReader().lineSequence()