package proxy.order.proxyChain;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 인터셉터 체인을 관리하면서 proceed() 호출될 때마다 다음 인터셉터로 넘어가고, 마지막에 타켓 메서드 호출
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    private final Object target;
    private final Method method;
    private final Object[] args;
    private final List<MethodInterceptor> interceptors;
    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object target,
                                      Method method,
                                      Object[] args,
                                      List<MethodInterceptor> interceptors) {
        this.target = target;
        this.method = method;
        this.args = (args != null ? args : new Object[0]);
        this.interceptors = interceptors;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public Object proceed() throws Throwable {
        //다음 인터셉터로 진행
        currentInterceptorIndex++;

        if (currentInterceptorIndex == interceptors.size()) {
            //더이상 인터셉터없음 메서드 호출
            return method.invoke(target, args);
        }

        MethodInterceptor interceptor = interceptors.get(currentInterceptorIndex);
        return interceptor.invoke(this);
    }
}
