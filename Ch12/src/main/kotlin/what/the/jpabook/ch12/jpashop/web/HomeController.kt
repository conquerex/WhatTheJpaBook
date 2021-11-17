package what.the.jpabook.ch12.jpashop.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {
    @RequestMapping("/")
    fun home(model: Model?): String {
        return "home"
    }

    @ResponseBody
    @RequestMapping("/wait")
    @Throws(InterruptedException::class)
    fun wait(model: Model?): String {
        Thread.sleep(1000)
        return "response"
    }
}
