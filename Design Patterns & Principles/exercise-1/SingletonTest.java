public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test ===\n");

        System.out.println("1. Getting first Logger instance...");
        Logger logger1 = Logger.getInstance();
        System.out.println("   " + logger1.getInstanceInfo());

        System.out.println("\n2. Getting second Logger instance...");
        Logger logger2 = Logger.getInstance();
        System.out.println("   " + logger2.getInstanceInfo());

        System.out.println("\n3. Checking if both instances are the same:");
        System.out.println("   logger1 == logger2: " + (logger1 == logger2));
        System.out.println("   logger1.equals(logger2): " + logger1.equals(logger2));

        System.out.println("\n4. Testing logging functionality:");
        logger1.log("Application started");
        logger2.logWarning("This is a warning message");
        logger1.logError("This is an error message");

        System.out.println("\n5. Testing from different methods:");
        testFromAnotherMethod();
        testFromStaticContext();

        System.out.println("\n=== Test Complete ===");
    }

    public static void testFromAnotherMethod() {
        Logger logger = Logger.getInstance();
        System.out.println("   From another method - " + logger.getInstanceInfo());
        logger.log("Message from another method");
    }

    public static void testFromStaticContext() {
        Logger logger = Logger.getInstance();
        System.out.println("   From static context - " + logger.getInstanceInfo());
        logger.log("Message from static context");
    }
}