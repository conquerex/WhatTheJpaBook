package what.the.jpabook.sample

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sample")
class SampleController(
    @Value("\${rapidapi.url}") val url: String,
    @Value("\${rapidapi.host}") val host: String,
    @Value("\${rapidapi.key}") val key: String
) {

    @GetMapping("")
    fun getJudgeList() {
        val client = OkHttpClient()
        println(url)
        println(host)
        println(key)

        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("x-rapidapi-host", host)
            .addHeader("x-rapidapi-key", key)
            .build()

        val response = client.newCall(request).execute()

        println(">>>> " + (response.body?.string() ?: ""))
    }

}