import java.util.Scanner;

public class OrderSorting {

    
    static class Order {
        int orderId;
        String customerName;
        double totalPrice;

        public Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return orderId + " | " + customerName + " | ₹" + totalPrice;
        }
    }

    
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (orders[j].totalPrice > orders[j+1].totalPrice) {
                    
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
        }
    }

    
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }


    public static void displayOrders(Order[] orders) {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Order[] orders = {
            new Order(201, "Alice", 4500.0),
            new Order(202, "Bob", 1200.0),
            new Order(203, "Charlie", 9900.0),
            new Order(204, "Daisy", 3000.0),
            new Order(205, "Ethan", 7200.0)
        };

        System.out.println("Original Orders:");
        displayOrders(orders);

        System.out.println("\nSorted by Bubble Sort:");
        bubbleSort(orders.clone());  // Clone to preserve original for quick sort
        displayOrders(orders);

        System.out.println("\nSorted by Quick Sort:");
        Order[] quickSortedOrders = orders.clone();
        quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        displayOrders(quickSortedOrders);

        sc.close();
    }
}
