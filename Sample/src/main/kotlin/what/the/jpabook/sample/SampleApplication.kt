package what.the.jpabook.sample

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class SampleApplication

fun main(args: Array<String>) {
    runApplication<SampleApplication>(*args)
}
