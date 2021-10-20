import javax.persistence.EntityManager
import javax.persistence.Persistence

class JpaMain

fun main(args: Array<String>) {
    //엔티티 매니저 팩토리 생성
    val emf = Persistence.createEntityManagerFactory("jpabook")
    val em = emf.createEntityManager() //엔티티 매니저 생성

    val tx = em.transaction //트랜잭션 기능 획득

    try {
        tx.begin() //트랜잭션 시작
        logic(em) //비즈니스 로직
        tx.commit() //트랜잭션 커밋
    } catch (e: Exception) {
        e.printStackTrace()
        tx.rollback() //트랜잭션 롤백
    } finally {
        em.close() //엔티티 매니저 종료
    }

    emf.close() //엔티티 매니저 팩토리 종료
}

fun logic(em: EntityManager) {
    val id = "id1"
    val member = Member()
    member.id = id
    member.username = "샘플이름"
    member.age = 22

    //등록
    em.persist(member)

    //수정
    member.age = 44

    //한 건 조회
    val findMember: Member = em.find(Member::class.java, id)
    println(">>> findMember = " + findMember.username.toString() + ", age=" + findMember.age)

    //목록 조회
    val members: List<Member> = em.createQuery("select m from Member m", Member::class.java).resultList
    println(">>> members.size = " + members.size)

    //삭제
//    em.remove(member)
}