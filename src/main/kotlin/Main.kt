import modes.PRODUCT_REGISTRATION_MENU_OPTIONS
import modes.SELLER_MENU_OPTIONS
import modes.Seller
import modes.createAppSeller
import program.data.getAppSeller
import program.data.updateAppSeller
import java.io.File

fun main() {
    init()
    println("Opening Marketplace...")
    // An infinite loop is needed to implement switching modes without restarting the program
    while (true) {
        println("\nSelect mode")
        println("1. Seller mode\n2. Customer mode\n3. Exit marketplace")
        var selectedMode: Int? = null
        var selectedModeIsValid = false

        while (!selectedModeIsValid) {
            print("Select Mode: ")
            selectedMode = readlnOrNull()?.trim()?.toIntOrNull()
            selectedModeIsValid = selectedMode != null && selectedMode in 1..3
            if (!selectedModeIsValid) {
                println("Invalid choice. Available choices: 1, 2, 3")
            }
        }
        println() // JUST WANT AN EMPTY LINE
        if (selectedMode == 3) {
            // EXIT PROGRAM
            println("Exiting Marketplace...")
            break
        }
        when (selectedMode) {
            1 -> {
                println("Entering Seller mode...")
                sellerMode()
            }

            2 -> {
                println("Entering Customer mode...")
                customerMode()
            }
        }
    }

}

fun sellerMode() {
//     Check the appdata folder for a seller.marketplace file and parse it
//     Else create a default seller
    var seller: Seller? = getAppSeller()
    while (true) {
        if (seller == null) {
            println("No seller found. Create a new seller.")
            seller = createAppSeller()
            updateAppSeller(seller)
        }
        // Show seller menu
        println("\nWelcome ${seller.name}")
        println("1. Update seller's name\n2. Product registration\n3. Manage inventory\n4. Exit seller mode")
        var choice: Int? = null
        var choiceIsValid = false

        while (!choiceIsValid) {
            print("What would you like to do? ")
            choice = readlnOrNull()?.trim()?.toIntOrNull()
            choiceIsValid = choice != null && choice in 1..4
            if (!choiceIsValid) {
                println("Invalid choice. Available choices: 1, 2, 3, 4")
            }
        }
        if (choice == SELLER_MENU_OPTIONS["EXIT_SELLER_MODE"]) {
            println("\nLeaving Seller mode...")
            break
        } else if (choice == SELLER_MENU_OPTIONS["UPDATE_SELLER_NAME"]) {
            var updatedSellerName: String? = null
            var nameIsInvalid = true
            var cancelNameChange = false

            while (nameIsInvalid) {
                print("Enter a name (Enter -1 to go back): ")
                updatedSellerName = readlnOrNull()?.trim()
                nameIsInvalid = updatedSellerName == null || updatedSellerName.length < 2
                cancelNameChange = updatedSellerName == "-1"
                if (cancelNameChange) break
                if (nameIsInvalid) {
                    println("Invalid name. Name must have a length greater than 2\n")
                }
            }

            if (!cancelNameChange && updatedSellerName != null) {
                seller.name = updatedSellerName
                updateAppSeller(seller)
            }
        } else if (choice == SELLER_MENU_OPTIONS["PRODUCT_REGISTRATION"]) {
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
            }
            if (subMenuChoice == PRODUCT_REGISTRATION_MENU_OPTIONS["REGISTER_A_PRODUCT"]) {
                println("Register a product")
            }
            if (subMenuChoice == PRODUCT_REGISTRATION_MENU_OPTIONS["DELIST_A_PRODUCT"]) {
                println("Delist a product")
            }
        }
    }
}

fun customerMode() {
    println("To be built!")
}

fun init() {
    // Create the appdata and imported_sellers directories if they don't exist
    fun createRequiredDir(dirName: String) {
        val dir = File(dirName)
        println("Checking if $dirName directory exists...")
        try {
            if (!dir.exists()) {
                println("No $dirName directory exists. Attempting to create...")
                if (dir.mkdir()) {
                    println("Created $dirName directory.")
                } else {
                    println("Failed to create $dirName directory.")
                }
            } else {
                println("$dirName directory exists.")
            }
        } catch (ex: Exception) {
            println("Failed to create $dirName directory: ${ex.message}.")
        }
        println()
    }
    createRequiredDir("appdata")
    createRequiredDir("imported_sellers")
}