package what.the.jpabook.ch12.jpashop.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order {
    //==Getter, Setter==//
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    var id: Long? = null

    //==연관관계 메서드==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    var member //주문 회원
            : Member? = null
        set(member) {
            field = member
            member?.orders?.add(this)
        }

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    private var orderItems: MutableList<OrderItem> = ArrayList<OrderItem>()

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    var delivery //배송정보
            : Delivery? = null
        private set
    var orderDate //주문시간
            : Date? = null

    @Enumerated(EnumType.STRING)
    private var status //주문상태
            : OrderStatus? = null
    //==비즈니스 로직==//
    /** 주문 취소  */
    fun cancel() {
        if (delivery!!.getStatus() === DeliveryStatus.COMP) {
            throw RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }
        setStatus(OrderStatus.CANCEL)
        for (orderItem in orderItems) {
            orderItem.cancel()
        }
    }
    //==조회 로직==//
    /** 전체 주문 가격 조회  */
    val totalPrice: Int
        get() {
            var totalPrice = 0
            for (orderItem in orderItems) {
                totalPrice += orderItem.totalPrice
            }
            return totalPrice
        }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun setDelivery(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    fun getOrderItems(): List<OrderItem> {
        return orderItems
    }

    fun setOrderItems(orderItems: MutableList<OrderItem>) {
        this.orderItems = orderItems
    }

    fun getStatus(): OrderStatus? {
        return status
    }

    fun setStatus(status: OrderStatus?) {
        this.status = status
    }

    override fun toString(): String {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                '}'
    }

    companion object {
        //==생성 메서드==//
        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            val order = Order()
            order.member = member
            order.setDelivery(delivery)
            for (orderItem in orderItems) {
                order.addOrderItem(orderItem)
            }
            order.setStatus(OrderStatus.ORDER)
            order.orderDate = Date()
            return order
        }
    }
}
