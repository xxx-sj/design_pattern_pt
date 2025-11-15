package templateCallback;

public class Soldier {
    void runContext(Strategy strategy) {
        System.out.println("start");
        strategy.runStrategy();
        System.out.println("end");


    }
}
