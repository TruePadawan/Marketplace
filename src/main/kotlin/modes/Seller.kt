package modes

import kotlinx.serialization.Serializable
import product.Product
import java.time.LocalDateTime
import java.util.UUID

@Serializable
class Seller(
    var name: String,
    val sellerId: String = UUID.randomUUID().toString(),
    var lastUpdated: String = LocalDateTime.now().toString(),
    var products: List<Product> = listOf<Product>()
) {
    fun registerProduct(productName: String, productPrice: Double) = true
    fun addProductToInventory(productId: String) = true
    fun exportSellerData() = true
}

fun createAppSeller(): Seller {
    var sellerName: String? = null
    var sellerNameIsInvalid = true
    while (sellerNameIsInvalid) {
        print("Enter a name for the seller (length must be greater than 1): ")
        sellerName = readlnOrNull()?.trim()
        sellerNameIsInvalid = sellerName == null || sellerName.length < 2
        if (sellerNameIsInvalid) {
            println("Invalid name. Name must have a length greater than 2\n")
        }
    }
    return Seller(name = sellerName!!)
}