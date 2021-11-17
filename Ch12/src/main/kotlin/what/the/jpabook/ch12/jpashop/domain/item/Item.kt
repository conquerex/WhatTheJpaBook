package what.the.jpabook.ch12.jpashop.domain.item

import what.the.jpabook.ch12.jpashop.domain.Category
import what.the.jpabook.ch12.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    val id: Long? = null

    var name //이름
            : String? = null
    var price //가격
            = 0
    var stockQuantity //재고수량
            = 0

    @ManyToMany(mappedBy = "items")
    private val categories: List<Category> = ArrayList()

    //==Biz Method==//
    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity = restStock
    }
}