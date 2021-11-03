import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.persistence.Tuple
import javax.persistence.TypedQuery
import javax.persistence.criteria.*


fun main(args: Array<String>) {
    //엔티티 매니저 팩토리 생성
    val emf = Persistence.createEntityManagerFactory("jpabook")
    val em = emf.createEntityManager() //엔티티 매니저 생성

    // 예제
    val cb: CriteriaBuilder = em.criteriaBuilder
    val cq: CriteriaQuery<Member> = cb.createQuery(Member::class.java)
    val m: Root<Member> = cq.from(Member::class.java)
    cq.select(m)
    val query: TypedQuery<Member> = em.createQuery(cq)
    val members: List<Member> = query.resultList

    members.forEach {
        println(">>> " + it.age)
    }

    // 예제
    val usernameEqual: Predicate = cb.equal(m.get<String>("username"), "샘플 ddd")
    val ageDesc: Order = cb.desc(m.get<Int>("age"))
    cq.select(m)
        .where(usernameEqual)
        .orderBy(ageDesc)
    em.createQuery(cq).resultList.forEach {
        println(">>> age : " + it.age)
    }

    // 예제
    val cb2: CriteriaBuilder = em.criteriaBuilder
    val cq2: CriteriaQuery<Object> = cb2.createQuery(Object::class.java)
    val cq2_1: CriteriaQuery<Tuple> = cb2.createQuery(Tuple::class.java)
    val list: List<Object> = em.createQuery(cq2).resultList
    println(">>>> size : ${list.size}")

    // 예제
    cq.select(m).distinct(true)
//    cq.multiselect(m.get<String>("username", m.get<Int>("age")))
//    cq.select(cb.array(m.get<String>("username", m.get<Int>("age"))))



    emf.close() //엔티티 매니저 팩토리 종료
}

fun logic(em: EntityManager) {
    val id = "id4"
    val member = Member()
    member.id = id
    member.username = "샘플이름"
    member.age = 22

    //등록
    em.persist(member)

    //수정
    member.age = 19

    //한 건 조회
    val findMember: Member = em.find(Member::class.java, id)
    println(">>> findMember = " + findMember.username.toString() + ", age=" + findMember.age)

    //목록 조회
    val members: List<Member> = em.createQuery("select m from Member m", Member::class.java).resultList
    println(">>> members.size = " + members.size)

    //삭제
//    em.remove(member)
}