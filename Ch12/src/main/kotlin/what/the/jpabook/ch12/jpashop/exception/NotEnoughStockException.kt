package what.the.jpabook.ch12.jpashop.exception

/**
 * 재고 부족 예외
 */
class NotEnoughStockException : RuntimeException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(cause: Throwable?) : super(cause) {}
}
