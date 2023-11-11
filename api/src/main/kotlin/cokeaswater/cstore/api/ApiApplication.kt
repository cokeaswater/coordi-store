package cokeaswater.cstore.api

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.event.EventListener

@SpringBootApplication
@ComponentScan(basePackages = ["cokeaswater.cstore"])
class ApiApplication
val log = KotlinLogging.logger {  }
fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}


