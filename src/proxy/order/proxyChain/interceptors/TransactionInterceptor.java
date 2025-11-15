package proxy.order.proxyChain.interceptors;

import proxy.order.SimpleTransactionManager;
import proxy.order.proxyChain.MethodInterceptor;
import proxy.order.proxyChain.MethodInvocation;

public class TransactionInterceptor implements MethodInterceptor {
    private final SimpleTransactionManager txManager;

    public TransactionInterceptor(SimpleTransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        txManager.begin();
        try {
            Object result = invocation.proceed(); // 다음 인터셉터 or 타겟 메서드
            txManager.commit();
            return result;
        } catch (RuntimeException e) {
            txManager.rollback();
            throw e;
        }
    }
}
