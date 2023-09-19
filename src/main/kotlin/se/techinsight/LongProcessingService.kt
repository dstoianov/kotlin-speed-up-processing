package se.techinsight

import java.util.concurrent.TimeUnit
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class LongProcessingService {

    private val log = LoggerFactory.getLogger(javaClass)

    fun process() {
        TimeUnit.MILLISECONDS.sleep(550)
        log.info("Long heavy process was done")
    }

    @Async
    fun processAsync() {
        TimeUnit.MILLISECONDS.sleep(550)
        log.info("Long heavy async process was done")
    }

}