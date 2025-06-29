import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventorySystem {

    // Product class
    static class Product {
        int productId;
        String productName;
        int quantity;
        double price;

        public Product(int productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return productId + " | " + productName + " | Qty: " + quantity + " | Price: ₹" + price;
        }
    }

    // Inventory Manager class
    static class InventoryManager {
        private Map<Integer, Product> inventory = new HashMap<>();

        // Add product
        public void addProduct(Product product) {
            if (inventory.containsKey(product.productId)) {
                System.out.println("❌ Product with ID " + product.productId + " already exists.");
            } else {
                inventory.put(product.productId, product);
                System.out.println("✅ Product added.");
            }
        }

        // Update product
        public void updateProduct(int productId, String newName, int newQty, double newPrice) {
            if (inventory.containsKey(productId)) {
                Product p = inventory.get(productId);
                p.productName = newName;
                p.quantity = newQty;
                p.price = newPrice;
                System.out.println("✅ Product updated.");
            } else {
                System.out.println("❌ Product not found.");
            }
        }

        // Delete product
        public void deleteProduct(int productId) {
            if (inventory.containsKey(productId)) {
                inventory.remove(productId);
                System.out.println("✅ Product deleted.");
            } else {
                System.out.println("❌ Product not found.");
            }
        }

        // Display all products
        public void displayInventory() {
            if (inventory.isEmpty()) {
                System.out.println("📦 Inventory is empty.");
            } else {
                System.out.println("📋 Inventory List:");
                for (Product p : inventory.values()) {
                    System.out.println(p);
                }
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Inventory Menu =====");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Inventory");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    manager.addProduct(new Product(id, name, qty, price));
                    break;
                case 2:
                    System.out.print("Enter Product ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int newQty = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();
                    manager.updateProduct(uid, newName, newQty, newPrice);
                    break;
                case 3:
                    System.out.print("Enter Product ID to delete: ");
                    int did = sc.nextInt();
                    manager.deleteProduct(did);
                    break;
                case 4:
                    manager.displayInventory();
                    break;
                case 5:
                    System.out.println("👋 Exiting Inventory System.");
                    sc.close();
                    return;
                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }
        }
    }
}
