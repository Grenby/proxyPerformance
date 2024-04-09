package com.example.performance.workers;

import com.example.performance.Worker;

import java.lang.reflect.Proxy;

public class ProxyWorker implements Worker {
    @Override
    public int work() {
        return 0;
    }

    private ProxyWorker(){}

    public static Worker proxyWorker(){
        ProxyWorker worker = new ProxyWorker();
        return (Worker) Proxy.newProxyInstance(
                worker.getClass().getClassLoader(),
                worker.getClass().getInterfaces(),
                (proxy, method, args) -> method.invoke(worker, args)
        );
    }

}
