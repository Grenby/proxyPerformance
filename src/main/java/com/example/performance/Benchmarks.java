package com.example.performance;

import com.example.performance.workers.*;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Fork(5)
@SpringBootApplication
public class Benchmarks {

    private Worker usual;
    private Worker proxy;
    private Worker byteBuddy;
    private Worker cglib;
    private Worker aspect;

    @Param({"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
            "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "36", "37", "38", "39","40"})
    private int tokens;

    @Setup
    public void setup() {
        var context = new SpringApplication(Benchmarks.class).run();
        usual = new UsualWorker();
        proxy = ProxyWorker.proxyWorker();
        byteBuddy = ByteBuddyProxy.byteBuddyWorker();
        cglib = CglibProxy.cglibProxy();
        aspect = context.getBean(AspectWorker.class);
    }


    @Benchmark
    public void usual() {
        Blackhole.consumeCPU(tokens);
        usual.work();
    }

    @Benchmark
    public void proxy() {

        Blackhole.consumeCPU(tokens);
        proxy.work();
    }

    @Benchmark
    public void byteBuddy() {
        Blackhole.consumeCPU(tokens);
        byteBuddy.work();
    }

    @Benchmark
    public void cglib() {
        Blackhole.consumeCPU(tokens);
        cglib.work();
    }

    @Benchmark
    public void aspect() {
        Blackhole.consumeCPU(tokens);
        aspect.work();
    }
}
