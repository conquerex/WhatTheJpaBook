package what.the.jpabook.ch12.jpashop.domain

import what.the.jpabook.ch12.jpashop.domain.item.Item
import javax.persistence.*

@Entity
@Table(name = "ORDER_ITEM")
class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    var item //주문 상품
            : Item? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    var order //주문
            : Order? = null
    var orderPrice //주문 가격
            = 0
    var count //주문 수량
            = 0
    //==비즈니스 로직==//
    /** 주문 취소  */
    fun cancel() {
        item?.addStock(count)
    }
    //==조회 로직==//
    /** 주문상품 전체 가격 조회  */
    val totalPrice: Int
        get() = orderPrice * count

    override fun toString(): String {
        return "OrderItem{" +
                "id=" + id +
                ", buyPrice=" + orderPrice +
                ", count=" + count +
                '}'
    }

    companion object {
        //==생성 메서드==//
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem()
            orderItem.item = item
            orderItem.orderPrice = orderPrice
            orderItem.count = count
            item.removeStock(count)
            return orderItem
        }
    }
}
