package cokeaswater.cstore.catalog.config

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@ComponentScan(basePackages = ["cokeaswater.cstore"])
class CatalogDomainConfiguration {

    val log = KotlinLogging.logger { }

    @Bean
    fun operationQueExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor();
        executor.corePoolSize = 2
        executor.maxPoolSize = 5
        executor.queueCapacity = 100
        executor.setRejectedExecutionHandler { r, _ -> log.info { "## Async Operation Rejected : $r" } }
        executor.setThreadNamePrefix("operation-que-")
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.initialize()
        return executor
    }

    @Bean
    fun domainQueExecutor(): TaskExecutor {
        val executor = ThreadPoolTaskExecutor();
        executor.corePoolSize = 2
        executor.maxPoolSize = 5
        executor.queueCapacity = 100
        executor.setRejectedExecutionHandler { r, _ -> log.info { "## Async Operation Rejected : $r" } }
        executor.setThreadNamePrefix("domain-que-")
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.initialize()
        return executor
    }
}
