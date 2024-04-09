package com.example.performance;

import com.example.performance.workers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Test.class, args);

        Worker worker = context.getBean(AspectWorker.class);


        Worker usual = new UsualWorker();
        Worker cglib = CglibProxy.cglibProxy();
        Worker proxy = ProxyWorker.proxyWorker();
        Worker byteBuddy = ByteBuddyProxy.byteBuddyWorker();

        System.out.println(usual.work());
        System.out.println(cglib.work());
        System.out.println(proxy.work());
        System.out.println(byteBuddy.work());
        System.out.println(worker.work());

    }

}
