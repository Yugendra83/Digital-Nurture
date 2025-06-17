
interface PaymentProcessor {
    boolean processPayment(double amount, String currency);
    String getPaymentStatus();
    void refundPayment(String transactionId);
}




class PayPalGateway {
    public String makePayment(double amount, String currencyCode) {
        System.out.println("Processing $" + amount + " " + currencyCode + " payment through PayPal...");
        
        try {
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "PAYPAL_SUCCESS_" + System.currentTimeMillis();
    }
    
    public String checkTransactionStatus(String transactionId) {
        return "PayPal Transaction " + transactionId + " completed successfully";
    }
    
    public boolean reverseTransaction(String transactionId) {
        System.out.println("PayPal: Reversing transaction " + transactionId);
        return true;
    }
}


class StripeGateway {
    public boolean chargeCard(double amountInCents, String currency) {
        System.out.println("Stripe: Charging " + (amountInCents/100) + " " + currency.toUpperCase());
        
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Math.random() > 0.1;     }
    
    public String getChargeInfo() {
        return "Stripe charge completed at " + new java.util.Date();
    }
    
    public void createRefund(String chargeId) {
        System.out.println("Stripe: Creating refund for charge " + chargeId);
    }
}


class SquareGateway {
    private String lastTransactionId;
    
    public int submitPayment(int amountCents, String currencyType) {
        System.out.println("Square: Processing payment of " + amountCents/100.0 + " " + currencyType);
      
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        int statusCode = Math.random() > 0.05 ? 200 : 400; 
        if (statusCode == 200) {
            lastTransactionId = "SQ_" + System.currentTimeMillis();
        }
        return statusCode;
    }
    
    public String getLastTransactionId() {
        return lastTransactionId;
    }
    
    public boolean voidTransaction(String transactionId) {
        System.out.println("Square: Voiding transaction " + transactionId);
        return true;
    }
}



class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway paypalGateway;
    private String lastTransactionId;
    
    public PayPalAdapter(PayPalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        try {
            lastTransactionId = paypalGateway.makePayment(amount, currency);
            return lastTransactionId != null && lastTransactionId.startsWith("PAYPAL_SUCCESS");
        } catch (Exception e) {
            System.err.println("PayPal payment failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getPaymentStatus() {
        if (lastTransactionId != null) {
            return paypalGateway.checkTransactionStatus(lastTransactionId);
        }
        return "No transaction found";
    }
    
    @Override
    public void refundPayment(String transactionId) {
        paypalGateway.reverseTransaction(transactionId != null ? transactionId : lastTransactionId);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    private String lastChargeId;
    
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        try {
            // Stripe expects amount in cents
            boolean success = stripeGateway.chargeCard(amount * 100, currency);
            if (success) {
                lastChargeId = "stripe_charge_" + System.currentTimeMillis();
            }
            return success;
        } catch (Exception e) {
            System.err.println("Stripe payment failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getPaymentStatus() {
        return stripeGateway.getChargeInfo();
    }
    
    @Override
    public void refundPayment(String transactionId) {
        stripeGateway.createRefund(transactionId != null ? transactionId : lastChargeId);
    }
}

class SquareAdapter implements PaymentProcessor {
    private SquareGateway squareGateway;
    
    public SquareAdapter(SquareGateway squareGateway) {
        this.squareGateway = squareGateway;
    }
    
    @Override
    public boolean processPayment(double amount, String currency) {
        try {
            
            int statusCode = squareGateway.submitPayment((int)(amount * 100), currency);
            return statusCode == 200;
        } catch (Exception e) {
            System.err.println("Square payment failed: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public String getPaymentStatus() {
        String transactionId = squareGateway.getLastTransactionId();
        return transactionId != null ? 
            "Square transaction " + transactionId + " processed successfully" : 
            "No Square transaction found";
    }
    
    @Override
    public void refundPayment(String transactionId) {
        String txnId = transactionId != null ? transactionId : squareGateway.getLastTransactionId();
        squareGateway.voidTransaction(txnId);
    }
}


class PaymentService {
    private PaymentProcessor processor;
    
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }
    
    public void setPaymentProcessor(PaymentProcessor processor) {
        this.processor = processor;
    }
    
    public boolean processOrder(double amount, String currency) {
        System.out.println("\n=== Processing Order ===");
        System.out.println("Amount: $" + amount + " " + currency);
        
        boolean success = processor.processPayment(amount, currency);
        
        if (success) {
            System.out.println("Payment Status: " + processor.getPaymentStatus());
            System.out.println("Order processed successfully!");
        } else {
            System.out.println("Payment failed!");
        }
        
        return success;
    }
    
    public void refundOrder(String transactionId) {
        System.out.println("\n=== Processing Refund ===");
        processor.refundPayment(transactionId);
        System.out.println("Refund initiated.");
    }
}


public class AdapterPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Payment Processing System with Adapter Pattern ===\n");
        

        PayPalGateway paypalGateway = new PayPalGateway();
        StripeGateway stripeGateway = new StripeGateway();
        SquareGateway squareGateway = new SquareGateway();
        
       
        PaymentProcessor paypalAdapter = new PayPalAdapter(paypalGateway);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripeGateway);
        PaymentProcessor squareAdapter = new SquareAdapter(squareGateway);
        
        
        PaymentService paymentService = new PaymentService(paypalAdapter);
        

        
       
        System.out.println(">>> Testing PayPal Gateway <<<");
        paymentService.processOrder(99.99, "USD");
        paymentService.refundOrder("PAYPAL_SUCCESS_123456");
        
        
        System.out.println("\n>>> Testing Stripe Gateway <<<");
        paymentService.setPaymentProcessor(stripeAdapter);
        paymentService.processOrder(149.50, "EUR");
        paymentService.refundOrder("stripe_charge_123456");
        
        
        System.out.println("\n>>> Testing Square Gateway <<<");
        paymentService.setPaymentProcessor(squareAdapter);
        paymentService.processOrder(75.25, "CAD");
        paymentService.refundOrder("SQ_123456");
        
        
        System.out.println("\n>>> Demonstrating Polymorphism <<<");
        PaymentProcessor[] processors = {paypalAdapter, stripeAdapter, squareAdapter};
        String[] gateways = {"PayPal", "Stripe", "Square"};
        
        for (int i = 0; i < processors.length; i++) {
            System.out.println("\nProcessing with " + gateways[i] + ":");
            paymentService.setPaymentProcessor(processors[i]);
            paymentService.processOrder(25.00, "USD");
        }
        
        System.out.println("\n=== Adapter Pattern Demo Complete ===");
    }
}


class PaymentFactory {
    public static PaymentProcessor createPaymentProcessor(String gatewayType) {
        switch (gatewayType.toLowerCase()) {
            case "paypal":
                return new PayPalAdapter(new PayPalGateway());
            case "stripe":
                return new StripeAdapter(new StripeGateway());
            case "square":
                return new SquareAdapter(new SquareGateway());
            default:
                throw new IllegalArgumentException("Unknown gateway type: " + gatewayType);
        }
    }
}