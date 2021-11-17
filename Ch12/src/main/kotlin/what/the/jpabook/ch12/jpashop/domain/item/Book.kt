package what.the.jpabook.ch12.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book : Item() {
    var author: String? = null
    var isbn: String? = null
}
