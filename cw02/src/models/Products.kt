package models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Products : IntIdTable("PRODUCTS") {
    val title = varchar("title", 50)
    val country = varchar("country", 50)
    val city = varchar("city", 50)
    val price = integer("price")
}

class Product(id: EntityID<Int>) : IntEntity(id) {
    companion object : EntityClass<Int, Product>(Products)

    var title by Products.title
    var country by Products.country
    var city by Products.city
    var price by Products.price
}

@Serializable
data class ProductModel(
    val title: String,
    val country: String,
    val city: String,
    val price: Int
){
    companion object {
        fun from(p: Product) = ProductModel(p.title, p.country, p.city, p.price)
    }
}
