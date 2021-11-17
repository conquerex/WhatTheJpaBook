package what.the.jpabook.ch12.jpashop.domain

import javax.persistence.*

@Entity
class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    var id: Long? = null
    var name: String? = null

    @Embedded
    var address: Address? = null

    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()

    override fun toString(): String {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}'
    }
}
