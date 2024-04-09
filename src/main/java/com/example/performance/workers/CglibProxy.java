package com.example.performance.workers;

import com.example.performance.Worker;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class CglibProxy implements Worker {
    @Override
    public int work() {
        return 0;
    }

    public static Worker cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProxy.class);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy)->proxy.invokeSuper(obj, args));
        return (Worker) enhancer.create();
    }

}
