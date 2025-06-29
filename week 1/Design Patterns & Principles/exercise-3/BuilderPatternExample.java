class Computer {
    private final String cpu;
    private final String ram;
    private final String storage;
    private final String gpu;
    private final String motherboard;
    private final String powerSupply;
    private final String coolingSystem;
    private final String caseType;
    private final boolean hasWiFi;
    private final boolean hasBluetooth;
    private final boolean hasRGBLighting;
    private final String operatingSystem;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
        this.coolingSystem = builder.coolingSystem;
        this.caseType = builder.caseType;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
        this.hasRGBLighting = builder.hasRGBLighting;
        this.operatingSystem = builder.operatingSystem;
    }

    public String getCpu() { return cpu; }
    public String getRam() { return ram; }
    public String getStorage() { return storage; }
    public String getGpu() { return gpu; }
    public String getMotherboard() { return motherboard; }
    public String getPowerSupply() { return powerSupply; }
    public String getCoolingSystem() { return coolingSystem; }
    public String getCaseType() { return caseType; }
    public boolean hasWiFi() { return hasWiFi; }
    public boolean hasBluetooth() { return hasBluetooth; }
    public boolean hasRGBLighting() { return hasRGBLighting; }
    public String getOperatingSystem() { return operatingSystem; }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage = "256GB SSD";
        private String gpu = "Integrated Graphics";
        private String motherboard = "Standard ATX";
        private String powerSupply = "500W";
        private String coolingSystem = "Stock Cooler";
        private String caseType = "Mid Tower";
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;
        private boolean hasRGBLighting = false;
        private String operatingSystem = "No OS";

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder motherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder powerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }

        public Builder coolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        public Builder caseType(String caseType) {
            this.caseType = caseType;
            return this;
        }

        public Builder hasWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        public Builder hasBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Builder hasRGBLighting(boolean hasRGBLighting) {
            this.hasRGBLighting = hasRGBLighting;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Computer build() {
            if (cpu == null || cpu.trim().isEmpty()) {
                throw new IllegalStateException("CPU is required");
            }
            if (ram == null || ram.trim().isEmpty()) {
                throw new IllegalStateException("RAM is required");
            }
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Computer Configuration:\n");
        sb.append("├── CPU: ").append(cpu).append("\n");
        sb.append("├── RAM: ").append(ram).append("\n");
        sb.append("├── Storage: ").append(storage).append("\n");
        sb.append("├── GPU: ").append(gpu).append("\n");
        sb.append("├── Motherboard: ").append(motherboard).append("\n");
        sb.append("├── Power Supply: ").append(powerSupply).append("\n");
        sb.append("├── Cooling: ").append(coolingSystem).append("\n");
        sb.append("├── Case: ").append(caseType).append("\n");
        sb.append("├── WiFi: ").append(hasWiFi ? "Yes" : "No").append("\n");
        sb.append("├── Bluetooth: ").append(hasBluetooth ? "Yes" : "No").append("\n");
        sb.append("├── RGB Lighting: ").append(hasRGBLighting ? "Yes" : "No").append("\n");
        sb.append("└── OS: ").append(operatingSystem).append("\n");
        return sb.toString();
    }
}

class ComputerTemplates {
    public static Computer createBasicOfficeComputer() {
        return new Computer.Builder("Intel Core i3-12100", "8GB DDR4")
                .storage("256GB SSD")
                .hasWiFi(true)
                .hasBluetooth(true)
                .operatingSystem("Windows 11 Home")
                .build();
    }

    public static Computer createGamingComputer() {
        return new Computer.Builder("AMD Ryzen 7 5800X", "32GB DDR4")
                .storage("1TB NVMe SSD")
                .gpu("NVIDIA RTX 4070")
                .motherboard("MSI B550 Gaming Plus")
                .powerSupply("750W 80+ Gold")
                .coolingSystem("Liquid Cooling AIO")
                .caseType("Full Tower RGB")
                .hasWiFi(true)
                .hasBluetooth(true)
                .hasRGBLighting(true)
                .operatingSystem("Windows 11 Pro")
                .build();
    }

    public static Computer createWorkstationComputer() {
        return new Computer.Builder("Intel Core i9-13900K", "64GB DDR5")
                .storage("2TB NVMe SSD")
                .gpu("NVIDIA RTX 4080")
                .motherboard("ASUS ProArt Z790")
                .powerSupply("1000W 80+ Platinum")
                .coolingSystem("Custom Loop Cooling")
                .caseType("Workstation Tower")
                .hasWiFi(true)
                .hasBluetooth(true)
                .operatingSystem("Windows 11 Pro")
                .build();
    }
}

public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Builder Pattern Demo - Computer Configuration ===\n");

        System.out.println("1. BASIC COMPUTER (Minimal Configuration):");
        System.out.println("==========================================");
        Computer basicComputer = new Computer.Builder("Intel Core i5-12400", "16GB DDR4").build();
        System.out.println(basicComputer);

        System.out.println("2. CUSTOM GAMING COMPUTER (Full Configuration):");
        System.out.println("==============================================");
        Computer gamingComputer = new Computer.Builder("AMD Ryzen 9 5900X", "32GB DDR4")
                .storage("1TB NVMe SSD + 2TB HDD")
                .gpu("NVIDIA GeForce RTX 4090")
                .motherboard("ASUS ROG Strix X570-E")
                .powerSupply("850W 80+ Gold Modular")
                .coolingSystem("360mm AIO Liquid Cooler")
                .caseType("Full Tower Tempered Glass")
                .hasWiFi(true)
                .hasBluetooth(true)
                .hasRGBLighting(true)
                .operatingSystem("Windows 11 Pro")
                .build();
        System.out.println(gamingComputer);

        System.out.println("3. BUDGET COMPUTER (Selective Options):");
        System.out.println("======================================");
        Computer budgetComputer = new Computer.Builder("AMD Ryzen 5 5600G", "16GB DDR4")
                .storage("512GB SSD")
                .hasWiFi(true)
                .operatingSystem("Ubuntu 22.04 LTS")
                .build();
        System.out.println(budgetComputer);

        System.out.println("4. PREDEFINED TEMPLATES:");
        System.out.println("=======================");

        System.out.println("Office Computer Template:");
        Computer officeComputer = ComputerTemplates.createBasicOfficeComputer();
        System.out.println(officeComputer);

        System.out.println("Gaming Computer Template:");
        Computer gamingTemplate = ComputerTemplates.createGamingComputer();
        System.out.println(gamingTemplate);

        System.out.println("Workstation Computer Template:");
        Computer workstationComputer = ComputerTemplates.createWorkstationComputer();
        System.out.println(workstationComputer);

        System.out.println("5. VALIDATION DEMO:");
        System.out.println("==================");
        try {
            Computer invalidComputer = new Computer.Builder("", "16GB DDR4").build();
        } catch (IllegalStateException e) {
            System.out.println("✓ Validation working: " + e.getMessage());
        }

        try {
            Computer invalidComputer2 = new Computer.Builder("Intel i5", "").build();
        } catch (IllegalStateException e) {
            System.out.println("✓ Validation working: " + e.getMessage());
        }

        System.out.println("\n6. METHOD CHAINING DEMO:");
        System.out.println("========================");
        Computer methodChainComputer = new Computer.Builder("Intel i7-13700K", "32GB DDR5")
                .gpu("RTX 4070 Ti")
                .storage("1TB SSD")
                .hasWiFi(true)
                .hasBluetooth(true)
                .hasRGBLighting(true)
                .operatingSystem("Windows 11")
                .build();
        System.out.println("Method chaining allows fluent configuration:");
        System.out.println(methodChainComputer);
    }
}