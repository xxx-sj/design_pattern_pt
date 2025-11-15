package proxy.order;

public interface OrderService {
    void placeOrder(String userId, String productId, int quantity);
}
