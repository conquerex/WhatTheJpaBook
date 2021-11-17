package what.the.jpabook.ch12.jpashop.repository

import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import what.the.jpabook.ch12.jpashop.domain.Member
import what.the.jpabook.ch12.jpashop.domain.Order
import what.the.jpabook.ch12.jpashop.domain.OrderSearch
import what.the.jpabook.ch12.jpashop.domain.OrderStatus
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Predicate

@Repository
class OrderRepository {
    @PersistenceContext
    var em: EntityManager? = null
    fun save(order: Order?) {
        em!!.persist(order)
    }

    fun findOne(id: Long?): Order {
        return em!!.find(Order::class.java, id)
    }

    fun findAll(orderSearch: OrderSearch): List<Order> {
        val cb = em!!.criteriaBuilder
        val cq = cb.createQuery(
            Order::class.java
        )
        val o = cq.from(Order::class.java)

        val criteria: MutableList<Predicate> = ArrayList()

        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            // <OrderStatus>이 없으면 "Not enough information to infer type variable Y" 에러
            val status = cb.equal(o.get<OrderStatus>("status"), orderSearch.orderStatus)
            criteria.add(status)
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            val m: Join<Order, Member> = o.join<Order, Member>("member", JoinType.INNER) //회원과 조인
            val name = cb.like(m.get("name"), "%" + orderSearch.memberName.toString() + "%")
            criteria.add(name)
        }
        cq.where(cb.and(*criteria.toTypedArray()))
        val query = em!!.createQuery(cq).setMaxResults(1000) //최대 검색 1000 건으로 제한
        return query.resultList
    }
}
