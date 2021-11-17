package what.the.jpabook.ch12.jpashop.domain

import javax.persistence.Embeddable

@Embeddable
class Address {
    var city: String? = null
    var street: String? = null
    var zipcode: String? = null

    constructor() {}
    constructor(city: String?, street: String?, zipcode: String?) {
        this.city = city
        this.street = street
        this.zipcode = zipcode
    }

//    override fun toString(): String {
//        return "Address{" +
//                "city='" + city + '\'' +
//                ", street='" + street + '\'' +
//                ", zipcode='" + zipcode + '\'' +
//                '}'
//    }
//
//    override fun equals(o: Any?): Boolean {
//        if (this === o) return true
//        if (o !is Address) return false
//        val address = o
//        if (if (city != null) city != address.city else address.city != null) return false
//        if (if (street != null) street != address.street else address.street != null) return false
//        return if (if (zipcode != null) zipcode != address.zipcode else address.zipcode != null) false else true
//    }
//
//    override fun hashCode(): Int {
//        var result = if (city != null) city.hashCode() else 0
//        result = 31 * result + if (street != null) street.hashCode() else 0
//        result = 31 * result + if (zipcode != null) zipcode.hashCode() else 0
//        return result
//    }
}
