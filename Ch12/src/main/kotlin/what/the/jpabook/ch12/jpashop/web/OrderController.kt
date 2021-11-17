package what.the.jpabook.ch12.jpashop.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import what.the.jpabook.ch12.jpashop.domain.Member
import what.the.jpabook.ch12.jpashop.domain.Order
import what.the.jpabook.ch12.jpashop.domain.OrderSearch
import what.the.jpabook.ch12.jpashop.domain.item.Item
import what.the.jpabook.ch12.jpashop.service.ItemService
import what.the.jpabook.ch12.jpashop.service.MemberService
import what.the.jpabook.ch12.jpashop.service.OrderService

@Controller
class OrderController {
    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var itemService: ItemService

    @RequestMapping(value = ["/order"], method = [RequestMethod.GET])
    fun createForm(model: Model): String {
        val members: List<Member> = memberService.findMembers()
        val items: List<Item> = itemService.findItems()
        model.addAttribute("members", members)
        model.addAttribute("items", items)
        return "order/orderForm"
    }

    @RequestMapping(value = ["/order"], method = [RequestMethod.POST])
    fun order(
        @RequestParam("memberId") memberId: Long?,
        @RequestParam("itemId") itemId: Long?,
        @RequestParam("count") count: Int
    ): String {
        orderService.order(memberId, itemId, count)
        return "redirect:/orders"
    }

    @RequestMapping(value = ["/orders"], method = [RequestMethod.GET])
    fun orderList(@ModelAttribute("orderSearch") orderSearch: OrderSearch, model: Model): String {
        val orders: List<Order> = orderService.findOrders(orderSearch)
        model.addAttribute("orders", orders)
        return "order/orderList"
    }

    @RequestMapping(value = ["/orders/{orderId}/cancel"])
    fun processCancelBuy(@PathVariable("orderId") orderId: Long?): String {
        orderService.cancelOrder(orderId)
        return "redirect:/orders"
    }
}
