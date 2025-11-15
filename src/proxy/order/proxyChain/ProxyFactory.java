package proxy.order.proxyChain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    private final Object target;
    private final List<MethodInterceptor> interceptors;

    public ProxyFactory(Object target, List<MethodInterceptor> interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }

    public Object getProxy() {
        Class<?> targetClass = target.getClass();
        ClassLoader cl = targetClass.getClassLoader();
        Class<?>[] interfaces = targetClass.getInterfaces();

        // 내부 aop invocationHandler를 만들면서 proxy 생성.
        return Proxy.newProxyInstance(cl, interfaces, new AopInvocationHandler(target, interceptors));
    }

    private static class AopInvocationHandler implements InvocationHandler {
        private final Object target;
        private final List<MethodInterceptor> interceptors;

        private AopInvocationHandler(Object target, List<MethodInterceptor> interceptors) {
            this.target = target;
            this.interceptors = interceptors;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // equals(), hashCode() 같은 건 그냥 바로 target에 위임하는 식으로 처리해도 됨
            // 여기서는 단순화를 위해 전부 AOP 체인 태운다고 가정

            //실질적으로 호출되면 여기서 method invocation을 만들어서 인터셉터 체인을 호출함.
            MethodInvocation invocation =
                    new ReflectiveMethodInvocation(target, method, args, interceptors);

            // 인터셉터 체인 시작
            return invocation.proceed();
        }
    }
}
