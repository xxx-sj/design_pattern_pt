package proxy.image;

public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadImageFormDisk(); //비용 큼.
    }

    //비용이 큰 작업.
    private void loadImageFormDisk() {
        System.out.println("loading " + fileName);
    }


    @Override
    public void display() {
        System.out.println("displaying " + fileName);
    }
}
