package adapter;

/**
 * 외부 결제 시스템과 현재 시스템 adapter 역할.
 */
public class LibPaymentAdapter implements PaymentProcessor {
    private final LibPayment libPayment;

    public LibPaymentAdapter(LibPayment libPayment) {
        this.libPayment = libPayment;
    }
    @Override
    public void pay(int amount) {
        libPayment.payOfLib(amount);
    }
}
