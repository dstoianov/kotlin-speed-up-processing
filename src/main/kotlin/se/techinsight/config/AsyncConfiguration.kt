package se.techinsight.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@EnableAsync
@Configuration
class AsyncConfiguration {

    @Bean
    fun asyncExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 3
            maxPoolSize = 10
            queueCapacity = 15
        }
        executor.setThreadNamePrefix("AsyncExecutor-")
        executor.initialize()
        return executor
    }

    @Bean("eventExecutor")
    fun asyncEventExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor().apply {
            corePoolSize = 4
            maxPoolSize = 10
            queueCapacity = 15
        }
        executor.setThreadNamePrefix("EventExecutor-")
        executor.initialize()
        return executor
    }

}
