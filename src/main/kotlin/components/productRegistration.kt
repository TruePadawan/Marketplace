package components

import modes.PRODUCT_REGISTRATION_MENU_OPTIONS
import modes.Seller
import program.data.updateAppSeller

fun productRegistration(seller: Seller) {
    while (true) {
        println("\nProduct Registration")
        println("1. Register a product\n2. Delist a product\n3. Exit product registration")
        var subMenuChoice: Int? = null
        var subMenuChoiceIsValid = false

        while (!subMenuChoiceIsValid) {
            print("What would you like to do? ")
            subMenuChoice = readlnOrNull()?.trim()?.toIntOrNull()
            subMenuChoiceIsValid = subMenuChoice != null && subMenuChoice in 1..3
            if (!subMenuChoiceIsValid) {
                println("Invalid choice. Available choices: 1, 2, 3")
            }
        }

        if (subMenuChoice == PRODUCT_REGISTRATION_MENU_OPTIONS["EXIT_PRODUCT_REGISTRATION"]) {
            println("Exiting product registration...")
            break
        } else if (subMenuChoice == PRODUCT_REGISTRATION_MENU_OPTIONS["REGISTER_A_PRODUCT"]) {
            registerProduct(seller)
        } else if (subMenuChoice == PRODUCT_REGISTRATION_MENU_OPTIONS["DELIST_A_PRODUCT"]) {
            delistProduct(seller)
        }
    }
}

private fun registerProduct(seller: Seller) {
    var productDataIsValid = false
    var productName: String? = null
    var productPrice: Double? = null

    while (!productDataIsValid) {
        print("What is the name of the product? ")
        productName = readlnOrNull()?.trim()
        print("How much will it cost in naira? (Input a number) ")
        productPrice = readlnOrNull()?.trim()?.toDoubleOrNull()

        val nameIsValid = productName != null && productName.length > 2
        val priceIsValid = productPrice != null && productPrice > 0

        productDataIsValid = nameIsValid && priceIsValid
    }

    val successful = seller.registerProduct(productName!!, productPrice!!)
    if (successful) {
        println("Successfully registered ${productName}!")
        updateAppSeller(seller)
    } else println("Failed to register product!")
}

// Delist a product and remove all instances from the seller's inventory
private fun delistProduct(seller: Seller) {
    if (seller.products.isEmpty()) {
        println("You have not registered any product!")
        return
    }

    println("Delist a product")
    for ((index, product) in seller.products.withIndex()) {
        println("${index + 1}. ${product.name}")
    }
    print("What is the index of the product to be delisted? ")
    var productIndex = readlnOrNull()?.trim()?.toIntOrNull()
    var indexIsValid = productIndex != null && productIndex in 1..seller.products.size
    while (!indexIsValid) {
        println("Enter a valid index: 1-${seller.products.size}")
        productIndex = readlnOrNull()?.trim()?.toIntOrNull()
        indexIsValid = productIndex != null && productIndex in 1..seller.products.size
    }
    val delistedProduct = seller.products[productIndex!! - 1]
    val successful = seller.unregisterProduct(delistedProduct.id)
    if (successful) {
        println("Successfully delisted ${delistedProduct.name}!")
        updateAppSeller(seller)
    } else println("Failed to delist product!")
}