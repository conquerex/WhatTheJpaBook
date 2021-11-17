package what.the.jpabook.ch12.jpashop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import what.the.jpabook.ch12.jpashop.domain.item.Item
import what.the.jpabook.ch12.jpashop.repository.ItemRepository

@Service
@Transactional
class ItemService {
    @Autowired
    lateinit var itemRepository: ItemRepository

    fun saveItem(item: Item?) {
        if (item != null) {
            itemRepository.save(item)
        }
    }

    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    fun findOne(itemId: Long?): Item {
        return itemRepository.findOne(itemId)
    }
}
