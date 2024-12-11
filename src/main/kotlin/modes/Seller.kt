package modes

import kotlinx.serialization.Serializable
import product.Product
import java.time.LocalDateTime
import java.util.UUID

@Serializable
class Seller(
    var name: String,
    val id: String = UUID.randomUUID().toString(),
    var lastUpdated: String = LocalDateTime.now().toString(),
    var products: MutableList<Product> = mutableListOf<Product>()
) {
    fun registerProduct(productName: String, productPrice: Double): Boolean {
        val product = Product(name = productName, price = productPrice, sellerId = this.id)
        return products.add(product)
    }

    fun unregisterProduct(productId: String) = products.removeIf { it.id == productId }

    fun addProductToInventory(productId: String) {
        products.first() { it.id == productId }.isInInventory = true
    }

    fun removeProductFromInventory(productId: String) {
        products.first() { it.id == productId }.isInInventory = false
    }

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

// Seller menu options
val SELLER_MENU_OPTIONS = mapOf(
    "UPDATE_SELLER_NAME" to 1,
    "PRODUCT_REGISTRATION" to 2,
    "INVENTORY_MANAGEMENT" to 3,
    "EXIT_SELLER_MODE" to 4
)

// Product Registration sub-menu options
val PRODUCT_REGISTRATION_MENU_OPTIONS = mapOf(
    "REGISTER_A_PRODUCT" to 1,
    "DELIST_A_PRODUCT" to 2,
    "EXIT_PRODUCT_REGISTRATION" to 3,
)