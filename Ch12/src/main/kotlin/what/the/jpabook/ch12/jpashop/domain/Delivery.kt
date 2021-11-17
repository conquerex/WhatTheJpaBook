package what.the.jpabook.ch12.jpashop.domain

import javax.persistence.*

@Entity
class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    var id: Long? = null

    @OneToOne(mappedBy = "delivery")
    var order: Order? = null

    @Embedded
    var address: Address? = null

    @Enumerated(EnumType.STRING)
    private var status //ENUM [READY(준비), COMP(배송)]
            : DeliveryStatus? = null

    constructor() {}
    constructor(address: Address?) {
        this.address = address
        status = DeliveryStatus.READY
    }

    fun getStatus(): DeliveryStatus? {
        return status
    }

    fun setStatus(status: DeliveryStatus?) {
        this.status = status
    }

//    override fun toString(): String {
//        return "Delivery{" +
//                "id=" + id +
//                ", address=" + address +
//                ", status=" + status +
//                '}'
//    }
}
