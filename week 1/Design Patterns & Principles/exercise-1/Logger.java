public class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger instance created!");
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + getCurrentTimestamp() + ": " + message);
    }

    public void logError(String errorMessage) {
        System.err.println("[ERROR] " + getCurrentTimestamp() + ": " + errorMessage);
    }

    public void logWarning(String warningMessage) {
        System.out.println("[WARNING] " + getCurrentTimestamp() + ": " + warningMessage);
    }

    private String getCurrentTimestamp() {
        return java.time.LocalDateTime.now().toString();
    }

    public String getInstanceInfo() {
        return "Logger instance hash: " + this.hashCode();
    }
}