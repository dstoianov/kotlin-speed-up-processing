package se.techinsight

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpeedUpProcessingApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpeedUpProcessingApplication>(*args)
}
