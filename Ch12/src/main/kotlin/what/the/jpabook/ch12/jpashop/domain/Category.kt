package what.the.jpabook.ch12.jpashop.domain

import what.the.jpabook.ch12.jpashop.domain.item.Item
import javax.persistence.*

@Entity
class Category {
    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private val id: Long? = null

    private val name: String? = null

    @ManyToMany
    @JoinTable(
        name = "CATEGORY_ITEM",
        joinColumns = [JoinColumn(name = "CATEGORY_ID")],
        inverseJoinColumns = [JoinColumn(name = "ITEM_ID")]
    )
    private val items: List<Item> = ArrayList()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private val parent: Category? = null

    @OneToMany(mappedBy = "parent")
    private val child: List<Category> = ArrayList<Category>()
}