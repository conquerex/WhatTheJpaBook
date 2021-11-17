package what.the.jpabook.ch12.jpashop.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import what.the.jpabook.ch12.jpashop.domain.Address
import what.the.jpabook.ch12.jpashop.domain.Member
import what.the.jpabook.ch12.jpashop.service.ItemService
import what.the.jpabook.ch12.jpashop.service.MemberService

@Controller
class MemberController {
    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var itemService: ItemService

    @RequestMapping(value = ["/members/new"], method = [RequestMethod.GET])
    fun createForm(): String {
        return "members/createMemberForm"
    }

    @RequestMapping(value = ["/members/new"], method = [RequestMethod.POST])
    fun create(member: Member, city: String?, street: String?, zipcode: String?): String {
        val address = Address(city, street, zipcode)
        member.address = address
        memberService.join(member)
        return "redirect:/"
    }

    @RequestMapping(value = ["/members"], method = [RequestMethod.GET])
    fun list(model: Model): String {
        val members: List<Member> = memberService.findMembers()
        model.addAttribute("members", members)
        return "members/memberList"
    }
}
