package what.the.jpabook.ch12.jpashop.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import what.the.jpabook.ch12.jpashop.domain.Address
import what.the.jpabook.ch12.jpashop.domain.Member
import what.the.jpabook.ch12.jpashop.domain.item.Book
import javax.annotation.PostConstruct

@Service
class MockCreateService {
    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    lateinit var orderService: OrderService

    @PostConstruct
    fun initCreateMock() {
        val member = Member()
        member.name= "회원1"
        member.address = Address("서울", "강가", "123-123")
        memberService.join(member)
        val book: Book = createBook("시골개발자의 JPA 책", 20000, 10)
        itemService.saveItem(book)
        itemService.saveItem(createBook("토비의 봄", 40000, 20))
        orderService.order(member.id, book.id, 5)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book()
        book.name = name
        book.price = price
        book.stockQuantity = stockQuantity
        return book
    }
}
