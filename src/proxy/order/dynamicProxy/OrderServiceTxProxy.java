package proxy.order.dynamicProxy;

import proxy.order.OrderService;
import proxy.order.SimpleTransactionManager;

public class OrderServiceTxProxy implements OrderService {
    private final OrderService target;
    private final SimpleTransactionManager txManager;

    public OrderServiceTxProxy(OrderService target, SimpleTransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }


    @Override
    public void placeOrder(String userId, String productId, int quantity) {
        try {
            txManager.begin();                // 트랜잭션 시작
            target.placeOrder(userId, productId, quantity); // 진짜 서비스 호출
            txManager.commit();               // 성공 시 커밋
        } catch (RuntimeException e) {
            txManager.rollback();             // 실패 시 롤백
            throw e;
        }
    }
}
