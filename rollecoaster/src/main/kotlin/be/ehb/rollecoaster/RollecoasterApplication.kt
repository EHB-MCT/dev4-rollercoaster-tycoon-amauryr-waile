package be.ehb.rollecoaster

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RollecoasterApplication

fun main(args: Array<String>) {
	runApplication<RollecoasterApplication>(*args)
}
