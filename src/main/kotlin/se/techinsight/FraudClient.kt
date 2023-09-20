package se.techinsight

import java.util.concurrent.TimeUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class FraudClient {

    private val log = LoggerFactory.getLogger(javaClass)

    fun processViaCoroutinesLaunchAndForget() {
        CoroutineScope(Dispatchers.IO).launch {
            enrichObjectLongOperation()
        }
        log.info("Long heavy Coroutines async process was finished")
    }

    fun processViaCoroutinesInParallel() {
        runBlocking {
            enrichObjectLongOperation()
        }
        log.info("Long heavy Coroutines async process was done")
    }


    suspend fun enrichObjectLongOperation() {
        coroutineScope {
            log.info("Fetching data from multiple sources ...")
            val result1 = async(Dispatchers.IO) { process(200) }
            val result2 = async(Dispatchers.IO) { process(700) }
            val result3 = async(Dispatchers.IO) { process(300) }
            listOf(result1, result2, result3)
                .forEach { it.await() }
        }
    }

    private fun process(sleep: Long = 1000L) {
        TimeUnit.MILLISECONDS.sleep(sleep)
        log.info("Fraud client request, it takes $sleep..")
    }
}