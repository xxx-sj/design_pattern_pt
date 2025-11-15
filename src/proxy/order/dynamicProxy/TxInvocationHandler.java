package proxy.order.dynamicProxy;

import proxy.order.SimpleTransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 동적 프록시.
 * 이전 정적프록시는 메서드 하나마다 프록시 클래스를 따로 만들어줘야함.
 * Dynamic proxy를 쓰면 런타임에 프록시 클래스를 자동 생성해서 감싸줄 수 있음.
 */
public class TxInvocationHandler implements InvocationHandler {

    private final Object target;
    private final SimpleTransactionManager txManager;

    public TxInvocationHandler(Object target,
                               SimpleTransactionManager txManager) {
        this.target = target;
        this.txManager = txManager;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 특정 메서드에만 트랜잭션 적용하고 싶으면 method.getName() 으로 필터링도 가능
        // 특정 메서드에서만 프록시가 적용되도록 조건을 추가할 수 있음
        // 이때 method는 metaspace내에 저장되어있는 메서드 정보들임.
        if (method.getName().startsWith("place")) {
            try {
                txManager.begin();
                //method 를 호출하면서 target(실제 메서드) 와 args (호출 시 넘어온 인자들)를 실제 메서드에 넘겨서 호출가능.
                Object result = method.invoke(target, args);
                txManager.commit();
                return result;
            } catch (RuntimeException e) {
                txManager.rollback();
                throw e;
            }
        }

        // 트랜잭션 안 걸리는 일반 메서드
        return method.invoke(target, args);
    }
}
