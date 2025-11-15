package decorator;

public class ExtraShot extends CoffeeDecorator {
    public ExtraShot(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int cost() {
        return this.coffee.cost() + 500;
    }

    @Override
    public String getDescription() {
        return this.coffee.getDescription() + ", Extra shot";
    }
}
