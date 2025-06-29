interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    
    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer();
    }
    
    private void loadFromRemoteServer() {
        System.out.println("Loading image from remote server: " + filename);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image loaded successfully: " + filename);
    }
    
    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;
    private boolean isLoaded;
    
    public ProxyImage(String filename) {
        this.filename = filename;
        this.isLoaded = false;
    }
    
    @Override
    public void display() {
        if (!isLoaded) {
            realImage = new RealImage(filename);
            isLoaded = true;
        }
        realImage.display();
    }
}

public class ProxyPatternTest {
    public static void main(String[] args) {
        System.out.println("Creating proxy images...");
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        Image image3 = new ProxyImage("photo3.jpg");
        
        System.out.println("\nProxy images created (no loading yet)");
        
        System.out.println("\n--- First display of image1 ---");
        image1.display();
        
        System.out.println("\n--- Second display of image1 (cached) ---");
        image1.display();
        
        System.out.println("\n--- First display of image2 ---");
        image2.display();
        
        System.out.println("\n--- Second display of image2 (cached) ---");
        image2.display();
        
        System.out.println("\n--- First display of image3 ---");
        image3.display();
        
        System.out.println("\n--- Multiple displays (all cached) ---");
        image1.display();
        image2.display();
        image3.display();
    }
}