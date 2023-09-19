package se.techinsight

import java.util.UUID
import kotlin.system.measureTimeMillis
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class SimpleController(val processor: LongProcessingService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/process")
    fun process(): Map<String, UUID> {
        measureTimeMillis {
            processor.process()
        }.also { logger.info("Elapsed time is: $it") }
        return mapOf("id" to UUID.randomUUID())
    }

    @GetMapping("/process-async")
    fun processAsync(): Map<String, UUID> {
        measureTimeMillis {
            processor.processAsync()
        }.also { logger.info("Elapsed time is: $it") }
        return mapOf("id" to UUID.randomUUID())
    }


}