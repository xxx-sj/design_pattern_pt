package decorator;

public class VanillaSyrup extends CoffeeDecorator {
    public VanillaSyrup(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return coffee.cost() + 700;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Vanilla Syrup";
    }
}
