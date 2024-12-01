package product

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val productId: String,
    var name: String,
    val price: Double,
    var amount: Int,
    var isInInventory: Boolean,
    val sellerId: String
)