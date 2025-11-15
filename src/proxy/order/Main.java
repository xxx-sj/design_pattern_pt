package proxy.order;

import proxy.order.dynamicProxy.OrderServiceTxProxy;

public class Main {
    public static void main(String[] args) {
        //실제 구현체
        OrderService realService = new OrderServiceImpl();
        SimpleTransactionManager txManager = new SimpleTransactionManager();

        // 클라이언트 입장에선 OrderService로만 보임 => proxy
        OrderService service = new OrderServiceTxProxy(realService, txManager);

        service.placeOrder("user-1", "product-1", 2);
    }
}
