package what.the.jpabook.ch12

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Ch12Application

fun main(args: Array<String>) {
    runApplication<Ch12Application>(*args)
    println("\n\n* * * Ch12Application start * * *\n\n")
}
