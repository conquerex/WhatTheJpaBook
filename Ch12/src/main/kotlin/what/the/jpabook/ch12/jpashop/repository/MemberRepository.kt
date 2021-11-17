package what.the.jpabook.ch12.jpashop.repository

import org.springframework.stereotype.Repository
import what.the.jpabook.ch12.jpashop.domain.Member
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberRepository {
    @PersistenceContext
    var em: EntityManager? = null
    fun save(member: Member?) {
        em!!.persist(member)
    }

    fun findOne(id: Long?): Member {
        return em!!.find(Member::class.java, id)
    }

    fun findAll(): List<Member> {
        return em!!.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    fun findByName(name: String?): List<Member> {
        return em!!.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
    }
}
