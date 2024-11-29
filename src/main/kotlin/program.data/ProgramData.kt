package program.data

import kotlinx.serialization.json.Json
import modes.Seller

data class UpdateAppSellerOptions(
    val updateName: Boolean,
    val updateRegisteredProducts: Boolean,
    val updateInventory: Boolean
)

val DEFAULT_UPDATE_APP_SELLER_OPTIONS =
    UpdateAppSellerOptions(updateName = true, updateRegisteredProducts = false, updateInventory = false)

// This file will hold functions for managing the persisted program data - seller/customer data
fun getAppSeller(): Seller? = null

fun updateAppSeller(
    seller: Seller, options: UpdateAppSellerOptions = DEFAULT_UPDATE_APP_SELLER_OPTIONS
) {
}