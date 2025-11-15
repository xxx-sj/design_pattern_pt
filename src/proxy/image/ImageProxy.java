package proxy.image;

public class ImageProxy implements Image {
    private RealImage realImage;
    private final String fileName;

    private final Object lock;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
        this.lock = new Object();
    }


    @Override
    public void display() {
        if (realImage == null) {
            synchronized (lock) {
                realImage = new RealImage(fileName);
            }
        }

        System.out.println("[Proxy] before display");
        realImage.display();
    }
}
