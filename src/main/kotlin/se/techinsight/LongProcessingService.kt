package se.techinsight

import java.util.concurrent.TimeUnit
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class LongProcessingService {

    private val log = LoggerFactory.getLogger(javaClass)

    fun process(sleep: Long = 550L) {
        TimeUnit.MILLISECONDS.sleep(sleep)
        log.info("Long heavy process was done")
    }

    @Async
    fun processAsync(sleep: Long = 1000L) {
        TimeUnit.MILLISECONDS.sleep(sleep)
        log.info("Long heavy async process was done")
    }

}