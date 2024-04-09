package com.example.performance.workers;

import com.example.performance.Worker;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.objenesis.instantiator.ObjectInstantiator;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyProxy implements Worker {
    public static Worker byteBuddyWorker() {
        ByteBuddyProxy byteBuddyProxy = new ByteBuddyProxy();
        var userProxy = new ByteBuddy()
                .subclass(ByteBuddyProxy.class)
                .method(named("worker"))
                .intercept(InvocationHandlerAdapter.of(
                        (proxy, method, args12) -> method.invoke(byteBuddyProxy, args12)
                ))
                .make()
                .load(byteBuddyProxy.getClass().getClassLoader())
                .getLoaded();
        Objenesis objenesis = new ObjenesisStd();
        ObjectInstantiator<? extends ByteBuddyProxy> thingyInstantiator = objenesis.getInstantiatorOf(userProxy);
        return thingyInstantiator.newInstance();
    }

    @Override
    public int work() {
        return 0;
    }

}
