package decorator;

public class Americano implements Coffee {

    @Override
    public int cost() {
        return 3000;
    }

    @Override
    public String getDescription() {
        return "Americano";
    }
}
