package what.the.jpabook.ch12.jpashop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import what.the.jpabook.ch12.jpashop.domain.*
import what.the.jpabook.ch12.jpashop.domain.item.Item
import what.the.jpabook.ch12.jpashop.repository.MemberRepository
import what.the.jpabook.ch12.jpashop.repository.OrderRepository

@Service
@Transactional
class OrderService {
    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    var itemService: ItemService? = null

    /** 주문  */
    fun order(memberId: Long?, itemId: Long?, count: Int): Long {

        //엔티티 조회
        val member: Member = memberRepository.findOne(memberId)
        val item: Item = itemService!!.findOne(itemId)

        //배송정보 생성
        val delivery = Delivery(member.address)
        //주문상품 생성
        val orderItem: OrderItem = OrderItem.createOrderItem(item, item.price, count)
        //주문 생성
        val order: Order = Order.createOrder(member, delivery, orderItem)

        //주문 저장
        orderRepository.save(order)
        return order.id ?: -1
    }

    /** 주문 취소  */
    fun cancelOrder(orderId: Long?) {

        //주문 엔티티 조회
        val order: Order = orderRepository.findOne(orderId)

        //주문 취소
        order.cancel()
    }

    /** 주문 검색  */
    fun findOrders(orderSearch: OrderSearch): List<Order> {
        return orderRepository.findAll(orderSearch)
    }
}
