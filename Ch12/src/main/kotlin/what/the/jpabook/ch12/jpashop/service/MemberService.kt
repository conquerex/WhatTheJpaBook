package what.the.jpabook.ch12.jpashop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import what.the.jpabook.ch12.jpashop.domain.Member
import what.the.jpabook.ch12.jpashop.repository.MemberRepository

@Service
@Transactional
class MemberService {
    @Autowired
    lateinit var memberRepository: MemberRepository

    /**
     * 회원 가입
     */
    fun join(member: Member): Long {
        validateDuplicateMember(member) //중복 회원 검증
        memberRepository.save(member)
        return member.id ?: -1
    }

    private fun validateDuplicateMember(member: Member) {
        val findMembers: List<Member> = memberRepository.findByName(member.name)
        check(findMembers.isEmpty()) { "이미 존재하는 회원입니다." }
    }

    /**
     * 전체 회원 조회
     */
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long?): Member {
        return memberRepository.findOne(memberId)
    }
}
