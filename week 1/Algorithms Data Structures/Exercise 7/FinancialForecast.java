import java.util.Scanner;

public class FinancialForecast {

    // Recursive approach
    public static double forecastRecursive(double value, double rate, int years) {
        if (years == 0) return value;
        return forecastRecursive(value, rate, years - 1) * (1 + rate);
    }

    // Optimized using memoization (top-down)
    public static double forecastMemo(double value, double rate, int years, double[] memo) {
        if (years == 0) return value;
        if (memo[years] != 0) return memo[years];
        memo[years] = forecastMemo(value, rate, years - 1, memo) * (1 + rate);
        return memo[years];
    }

    // Iterative version (recommended in real systems)
    public static double forecastIterative(double value, double rate, int years) {
        for (int i = 1; i <= years; i++) {
            value *= (1 + rate);
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter initial value: ");
        double initial = sc.nextDouble();

        System.out.print("Enter annual growth rate (e.g., 0.05 for 5%): ");
        double rate = sc.nextDouble();

        System.out.print("Enter number of years to forecast: ");
        int years = sc.nextInt();

        // Recursive
        double recResult = forecastRecursive(initial, rate, years);
        System.out.println("\n📈 Recursive Forecast Result: ₹" + recResult);

        // Optimized with memoization
        double[] memo = new double[years + 1];
        double memoResult = forecastMemo(initial, rate, years, memo);
        System.out.println("💡 Memoized Forecast Result: ₹" + memoResult);

        // Iterative (Best for performance)
        double iterResult = forecastIterative(initial, rate, years);
        System.out.println("⚙️ Iterative Forecast Result: ₹" + iterResult);

        sc.close();
    }
}
