package se.techinsight.event

import java.util.concurrent.TimeUnit
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class AsyncFraudEventListener {

    private val log = LoggerFactory.getLogger(javaClass)

    @Async("eventExecutor")
    @EventListener
    fun onEvent(event: AsyncFraudEvent) {
        log.info("Processing Event started. ID: ${event.id}")
        TimeUnit.MILLISECONDS.sleep(750)
        log.info("Processing Event finished. ID: ${event.id}")
    }

}