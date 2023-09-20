package se.techinsight

import java.util.UUID
import kotlin.system.measureTimeMillis
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class SimpleController(
    val processor: LongProcessingService,
    val fraudClient: FraudClient,
    ) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/process")
    fun process(): Map<String, Any> {
        val res = measureTimeMillis {
            processor.process(650)
        }.also { logger.info("Elapsed time is: $it") }
        return mapOf("id" to UUID.randomUUID(), "elapsed_time" to res, "description" to "no async")
    }

    @GetMapping("/process-async")
    fun processAsync(): Map<String, Any> {
        val res = measureTimeMillis {
            processor.processAsync()
        }.also { logger.info("Elapsed time with Async is: $it") }
        return mapOf("id" to UUID.randomUUID(), "elapsed_time" to res, "description" to "spring boot async")
    }

    @GetMapping("/process-kotlinx")
    fun processCoroutines(): Map<String, Any> {
        val res = measureTimeMillis {
            fraudClient.processViaCoroutinesInParallel()
        }.also { logger.info("Elapsed time with Coroutines is: $it") }
        return mapOf("id" to UUID.randomUUID(), "elapsed_time" to res, "description" to "lunch coroutines in parallel")
    }

    @GetMapping("/process-kotlinx-launch")
    fun processCoroutinesAndForget(): Map<String, Any> {
        val res = measureTimeMillis {
            fraudClient.processViaCoroutinesLaunchAndForget()
        }.also { logger.info("Elapsed time with Coroutines is: $it") }
        return mapOf("id" to UUID.randomUUID(), "elapsed_time" to res, "description" to "lunch coroutines and forget, like async")
    }

}