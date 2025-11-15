package adapter;

public class PaymentService {
    private final PaymentProcessor processor;
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    //현재 사용중인 프로세스에서는 영향이 없음 OCP SRP
    public void pay(int amount) {
        processor.pay(amount);
    }
}
