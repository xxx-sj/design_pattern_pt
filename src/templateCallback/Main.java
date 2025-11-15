package templateCallback;

public class Main {
    public static void main(String[] args) {
        Strategy strategy = null;
        Soldier rambo = new Soldier();

        strategy = new StrategyGun();
        rambo.runContext(strategy);

        System.out.println();

        strategy = new StrategySword();
        rambo.runContext(strategy);


        System.out.println("====== template callback v1");

        rambo.runContext(new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println("template callback gun -");
            }
        });

        rambo.runContext(new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println("template callback sword-");
            }
        });

        System.out.println("====== template callback v2");

        rambo.runContext(executeWeapon("gun sound!"));
        rambo.runContext(executeWeapon("sword sound!"));
    }

    private static Strategy executeWeapon(String weaponSound) {
        return new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println(weaponSound);
            }
        };

    }
}
