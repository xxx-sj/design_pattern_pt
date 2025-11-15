package adapter;

/**
 * 내부 결제 시스템
 */
public interface PaymentProcessor {
    void pay(int amount);
}
