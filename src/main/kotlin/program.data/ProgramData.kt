// This file will hold functions for managing the persisted program data - seller/customer data
package program.data

import kotlinx.serialization.json.Json
import modes.Seller
import java.io.File

data class UpdateAppSellerOptions(
    val updateName: Boolean,
    val updateRegisteredProducts: Boolean,
    val updateInventory: Boolean
)

val DEFAULT_UPDATE_APP_SELLER_OPTIONS =
    UpdateAppSellerOptions(updateName = true, updateRegisteredProducts = false, updateInventory = false)

fun getAppSeller(): Seller? {
    val sellerFile = File("appdata", "seller.marketplace")
    if (sellerFile.exists()) {
        return Json.decodeFromString<Seller>(sellerFile.readText())
    }
    sellerFile.createNewFile()
    return null
}

fun updateAppSeller(
    seller: Seller, options: UpdateAppSellerOptions = DEFAULT_UPDATE_APP_SELLER_OPTIONS
) {
    /*
    * Write the seller data to the seller.marketplace file
    * The data written might depend on the 'options' parameter(This might not be needed,
    * will depend on whether all backing fields serialize properly to JSON)
    * */
    val stringifiedData = Json.encodeToString(Seller.serializer(), seller)
    try {
        val sellerFile = File("appdata", "seller.marketplace")
        sellerFile.createNewFile()
        sellerFile.writeText(stringifiedData)
    } catch (e: Exception) {
        println("Error while attempting to write to seller.marketplace file: ${e.message}")
    }
}