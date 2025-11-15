package proxy.order.proxyChain;

import proxy.order.OrderService;
import proxy.order.OrderServiceImpl;
import proxy.order.SimpleTransactionManager;
import proxy.order.proxyChain.interceptors.LoggingInterceptor;
import proxy.order.proxyChain.interceptors.TransactionInterceptor;

import java.util.Arrays;
import java.util.List;

public class MainAopApp {
    public static void main(String[] args) {
        // 1. Target 객체
        OrderService target = new OrderServiceImpl();

        // 2. 인터셉터들 준비 순서 중요함 순서대로 실행.
        SimpleTransactionManager txManager = new SimpleTransactionManager();
        MethodInterceptor txInterceptor = new TransactionInterceptor(txManager);
        MethodInterceptor logInterceptor = new LoggingInterceptor();

        // 체인 순서: 트랜잭션 → 로그 → 타겟
        List<MethodInterceptor> interceptors = Arrays.asList(
                txInterceptor,
                logInterceptor
        );

        // 3. Proxy 생성
        ProxyFactory proxyFactory = new ProxyFactory(target, interceptors);
        OrderService proxy = (OrderService) proxyFactory.getProxy();

        // 4. 사용
        proxy.placeOrder("user-1", "product-1", 2);
    }
}
