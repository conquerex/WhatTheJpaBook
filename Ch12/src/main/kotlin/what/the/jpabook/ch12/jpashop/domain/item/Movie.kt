package what.the.jpabook.ch12.jpashop.domain.item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie : Item() {
    var director: String? = null
    var actor: String? = null
}
