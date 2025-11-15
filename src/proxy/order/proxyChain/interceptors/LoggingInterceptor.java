package proxy.order.proxyChain.interceptors;

import proxy.order.proxyChain.MethodInterceptor;
import proxy.order.proxyChain.MethodInvocation;

import java.util.Arrays;

public class LoggingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("[LOG] method = " + invocation.getMethod().getName()
                + ", args = " + Arrays.toString(invocation.getArguments()));

        try {
            Object result = invocation.proceed();
            long end = System.currentTimeMillis();
            System.out.println("[LOG] method = " + invocation.getMethod().getName()
                    + " completed in " + (end - start) + "ms");
            return result;
        } catch (Throwable t) {
            System.out.println("[LOG] method = " + invocation.getMethod().getName()
                    + " threw exception: " + t);
            throw t;
        }
    }
}