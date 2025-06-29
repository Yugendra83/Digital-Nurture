interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;
    
    public NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
    
    @Override
    public void send(String message) {
        notifier.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("SMS: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Slack: " + message);
    }
}

public class DecoratorPatternTest {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        
        System.out.println("Sending notification via Email only:");
        emailNotifier.send("Hello World!");
        
        System.out.println("\nSending notification via Email and SMS:");
        Notifier emailSMSNotifier = new SMSNotifierDecorator(emailNotifier);
        emailSMSNotifier.send("Hello World!");
        
        System.out.println("\nSending notification via Email, SMS, and Slack:");
        Notifier allChannelsNotifier = new SlackNotifierDecorator(
            new SMSNotifierDecorator(emailNotifier)
        );
        allChannelsNotifier.send("Hello World!");
        
        System.out.println("\nSending notification via Email and Slack only:");
        Notifier emailSlackNotifier = new SlackNotifierDecorator(emailNotifier);
        emailSlackNotifier.send("Hello World!");
    }
}