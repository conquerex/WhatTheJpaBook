package what.the.jpabook.sample

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@SpringBootTest
class SampleApplicationTests {

    @Autowired
    lateinit var a: Sample

    @Test
    fun test() {
        println("Before, in test: " + Thread.currentThread())
        for (i in 0..5) {
            a.async()
        }

        for (i in 0..5) {
            a.sync()
        }
        println("After, in test: " + Thread.currentThread())
    }

    @Test
    fun test2() {
        println(LocalTime.now().toString() + " Start >>>")
        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        val future: Future<Int> = executor.submit<Int> {
            println(LocalTime.now().toString() + " Starting runnable")
            val sum = 1 + 1
            Thread.sleep(3000)
            sum
        }

        println(LocalTime.now().toString() + " Waiting the task done")
        val result: Int = future.get()
        println(LocalTime.now().toString() + " Result : " + result)
    }

}

@Component
class Sample {
    @Async("taskExecutor")
    fun async() {
        Thread.sleep(300)
        println("""
            In async: ${Thread.currentThread()} // ${LocalTime.now()}
        """.trimIndent())
    }

    fun sync() {
        Thread.sleep(100)
        println("""
            In sync: ${Thread.currentThread()} // ${LocalTime.now()}
        """.trimIndent())
    }
}