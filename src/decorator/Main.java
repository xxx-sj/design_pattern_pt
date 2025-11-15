package decorator;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new Americano();
        coffee = new ExtraShot(coffee);
        coffee = new VanillaSyrup(coffee);

        System.out.println(coffee.getDescription()); // Americano, Extra Shot, Vanilla Syrup
        System.out.println(coffee.cost());           // 4200

    }
}
