package adapter;

public class Main {
    public static void main(String[] args) {

        //adapter
        LibPayment libPayment = new LibPayment();
        LibPaymentAdapter adapter = new LibPaymentAdapter(libPayment);


        //inject adapter
        PaymentService service = new PaymentService(adapter);

        service.pay(1000);
    }
}
