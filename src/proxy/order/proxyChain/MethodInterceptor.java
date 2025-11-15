package proxy.order.proxyChain;

public interface MethodInterceptor {
    Object invoke(MethodInvocation invocation) throws Throwable;
}
