package product

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Product(
    val productId: String = UUID.randomUUID().toString(),
    var name: String,
    val price: Double,
    var amount: Int = 0,
    var isInInventory: Boolean = false,
    val sellerId: String
)