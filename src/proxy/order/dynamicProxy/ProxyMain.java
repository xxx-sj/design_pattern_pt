package proxy.order.dynamicProxy;

import proxy.order.OrderService;
import proxy.order.OrderServiceImpl;
import proxy.order.SimpleTransactionManager;

import java.lang.reflect.Proxy;

public class ProxyMain {
    public static void main(String[] args) {
        /**
         * orderService를 구현하는 새로운 클래스를 만들고
         * 그 안에서 invoke를 통해 트랜잭션을 감싸고
         * 실제 대상 객체를 호출한다 realService
         */
        OrderService realService = new OrderServiceImpl();
        SimpleTransactionManager txManager = new SimpleTransactionManager();

        OrderService proxy = (OrderService) Proxy.newProxyInstance(
                OrderService.class.getClassLoader(), // class laoder 정보와.
                new Class[]{OrderService.class},    // 인터페이스 목록
                new TxInvocationHandler(realService, txManager) // 프록시 적용할 class
        );

        proxy.placeOrder("user-1", "product-1", 2);
    }
}
