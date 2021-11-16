import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "MEMBER")
class Member {
    @Id
    @Column(name = "ID")
    var id: String? = null

    @Column(name = "NAME")
    var username: String? = null

    var age: Int? = null
}