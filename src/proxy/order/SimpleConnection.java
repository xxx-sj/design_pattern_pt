package proxy.order;

//실제 디비와 연결됨.
public class SimpleConnection {
    public void begin() {
        System.out.println("[TX] Begin");
    }

    public void commit() {
        System.out.println("[TX] COMMIT");
    }

    public void rollback() {
        System.out.println("[TX] ROLLBACK");
    }
}
