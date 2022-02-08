package what.the.jpabook.sample

import org.slf4j.MDC
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskDecorator
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableAsync
class AsyncConfig(
    @Value("\${sample.poolsize}")
    private val poolSize: Int,
) {
    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val taskExecutor = ThreadPoolTaskExecutor()

        taskExecutor.setThreadNamePrefix("QueueTask-")
        taskExecutor.corePoolSize = poolSize
        taskExecutor.maxPoolSize = poolSize * 2
        taskExecutor.setQueueCapacity(poolSize * 5)
        taskExecutor.setTaskDecorator(LoggingTaskDecorator())
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true)

        return taskExecutor
    }
}

/**
 * 각 비동기 쓰레드마다 로그를 남길 경우 자신을 호출한 쓰레드를 알 수 있어, 디버그시 추적이 용이해진다.
 */
class LoggingTaskDecorator : TaskDecorator {

    override fun decorate(task: Runnable): Runnable {
        val callerThreadContext = MDC.getCopyOfContextMap()

        return Runnable {
            callerThreadContext?.let {
                MDC.setContextMap(it)
            }
            task.run()
        }
    }
}
