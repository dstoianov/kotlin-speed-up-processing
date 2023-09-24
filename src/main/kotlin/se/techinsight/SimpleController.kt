package se.techinsight

import java.util.UUID
import kotlin.system.measureTimeMillis
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.techinsight.event.AsyncFraudEvent
import se.techinsight.event.CustomFraudEvent

@RestController
@RequestMapping("/api/v1")
class SimpleController(
    val processor: LongProcessingService,
    val fraudClient: FraudClient,
    val publisher: ApplicationEventPublisher,
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
            logger.info("Event was sent..")
        }.also { logger.info("Elapsed time with Coroutines is: $it") }
        return mapOf(
            "id" to UUID.randomUUID(),
            "elapsed_time" to res,
            "description" to "lunch coroutines and forget, like async"
        )
    }

//     Spring Boot Events,
//     1. Default implementation works synchronously
//     2. Process events Asynchronously

    @GetMapping("/process-event")
    fun processBasedOnEvents(): Map<String, Any> {
        val res = measureTimeMillis {
            publisher.publishEvent(CustomFraudEvent(name = "Event to process"))
        }.also { logger.info("Elapsed time with Spring Boot Events is: $it") }
        return mapOf(
            "id" to UUID.randomUUID(),
            "elapsed_time" to res,
            "description" to "based on Spring Events, by default, event listeners are synchronous in Spring Boot",
        )
    }

    @GetMapping("/process-event-async")
    fun processBasedOnEventsAsync(): Map<String, Any> {
        val res = measureTimeMillis {
            publisher.publishEvent(AsyncFraudEvent(name = "Event to process"))
        }.also { logger.info("Elapsed time with Spring Boot Events is: $it") }
        return mapOf(
            "id" to UUID.randomUUID(),
            "elapsed_time" to res,
            "description" to "based on Spring Events wich async processing",
        )
    }

}