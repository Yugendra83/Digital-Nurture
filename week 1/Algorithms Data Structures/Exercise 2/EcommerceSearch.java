import java.util.Arrays;
import java.util.Scanner;

public class EcommerceSearch {

   
    static class Product {
        int productId;
        String productName;
        String category;

        Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return productId + " | " + productName + " | " + category;
        }
    }

    
    public static int linearSearch(Product[] products, String key) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    
    public static int binarySearch(Product[] products, String key) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(key);
            if (cmp == 0) return mid;
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    
    public static void sortByName(Product[] products) {
        Arrays.sort(products, (p1, p2) -> p1.productName.compareToIgnoreCase(p2.productName));
    }

    
    public static void display(Product[] products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Phone", "Electronics"),
            new Product(103, "Shirt", "Clothing"),
            new Product(104, "Shoes", "Footwear"),
            new Product(105, "Watch", "Accessories")
        };

        System.out.println("📦 Product List:");
        display(products);

        System.out.print("\n🔍 Enter product name to search: ");
        String key = sc.nextLine();

        
        int linResult = linearSearch(products, key);
        System.out.println("\n📘 Linear Search Result:");
        if (linResult != -1) {
            System.out.println("Found: " + products[linResult]);
        } else {
            System.out.println("Product not found.");
        }

        
        sortByName(products);
        int binResult = binarySearch(products, key);
        System.out.println("\n📗 Binary Search Result (on sorted list):");
        if (binResult != -1) {
            System.out.println("Found: " + products[binResult]);
        } else {
            System.out.println("Product not found.");
        }

        sc.close();
    }
}
