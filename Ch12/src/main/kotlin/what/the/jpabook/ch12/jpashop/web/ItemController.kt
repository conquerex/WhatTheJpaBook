package what.the.jpabook.ch12.jpashop.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import what.the.jpabook.ch12.jpashop.domain.item.Book
import what.the.jpabook.ch12.jpashop.domain.item.Item
import what.the.jpabook.ch12.jpashop.service.ItemService

@Controller
class ItemController {

    @Autowired
    lateinit var itemService: ItemService

    @RequestMapping(value = ["/items/new"], method = [RequestMethod.GET])
    fun createForm(): String {
        return "items/createItemForm"
    }

    @RequestMapping(value = ["/items/new"], method = [RequestMethod.POST])
    fun create(item: Book?): String {
        itemService.saveItem(item)
        return "redirect:/items"
    }

    /**
     * 상품 수정 폼
     */
    @RequestMapping(value = ["/items/{itemId}/edit"], method = [RequestMethod.GET])
    fun updateItemForm(@PathVariable("itemId") itemId: Long?, model: Model): String {
        val item: Item = itemService.findOne(itemId)
        model.addAttribute("item", item)
        return "items/updateItemForm"
    }

    /**
     * 상품 수정
     */
    @RequestMapping(value = ["/items/{itemId}/edit"], method = [RequestMethod.POST])
    fun updateItem(@ModelAttribute("item") item: Book?): String {
        itemService.saveItem(item)
        return "redirect:/items"
    }

    /**
     * 상품 목록
     */
    @RequestMapping(value = ["/items"], method = [RequestMethod.GET])
    fun list(model: Model): String {
        val items: List<Item> = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }
}

