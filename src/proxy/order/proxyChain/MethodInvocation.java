package proxy.order.proxyChain;

import java.lang.reflect.Method;

public interface MethodInvocation {
    Method getMethod();
    Object[] getArguments();
    Object getThis(); // target object
    Object proceed() throws Throwable;
}
