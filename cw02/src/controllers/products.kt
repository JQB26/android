package controllers

import kotlinx.serialization.*

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import kotlinx.serialization.json.Json
import models.Product
import models.ProductModel
import models.Products
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.products(){
    route("/products") {
        get {
            val sb = StringBuilder()
            sb.append("[")
            transaction {
                for (p in Product.all()) {
                    val prod = ProductModel.from(p)
                    sb.append(Json.encodeToString(prod))
                    sb.append(",")
                }
            }
            sb.deleteCharAt(sb.length - 1)
            sb.append("]")
            call.respond(sb.toString())
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id"
            )

            val res = transaction {
                val prod = ProductModel.from(Product[id.toInt()])
                Json.encodeToString(prod)
            }
            call.respond(res)
        }
    }
}

fun Application.customerRoutes() {
    routing {
        products()
    }
}
