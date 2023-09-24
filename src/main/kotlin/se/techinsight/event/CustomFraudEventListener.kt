package se.techinsight.event

import java.util.concurrent.TimeUnit
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CustomFraudEventListener {

    private val log = LoggerFactory.getLogger(javaClass)

    /**
     * Starting with Spring 4.1 it’s now possible to simply annotate a method of a managed bean
     * with @EventListener to automatically register an ApplicationListener matching the signature of the method
     */
    @EventListener
    fun onEvent(event: CustomFraudEvent) {
        log.info("Processing Event started. ID: ${event.id}")
        TimeUnit.MILLISECONDS.sleep(550)
        log.info("Processing Event finished. ID: ${event.id}")
    }

}