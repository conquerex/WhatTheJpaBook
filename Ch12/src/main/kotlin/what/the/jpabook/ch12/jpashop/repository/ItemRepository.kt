package what.the.jpabook.ch12.jpashop.repository

import org.springframework.stereotype.Repository
import what.the.jpabook.ch12.jpashop.domain.item.Item
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemRepository {
    @PersistenceContext
    var em: EntityManager? = null
    fun save(item: Item) {
        if (item.id == null) {
            em!!.persist(item)
        } else {
            em!!.merge<Any>(item)
        }
    }

    fun findOne(id: Long?): Item {
        return em!!.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em!!.createQuery("select i from Item i", Item::class.java).getResultList()
    }
}
