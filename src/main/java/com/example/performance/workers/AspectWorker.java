package com.example.performance.workers;

import com.example.performance.AspectAnnotation;
import com.example.performance.Worker;
import org.springframework.stereotype.Component;

@Component
public class AspectWorker implements Worker {

    @Override
    @AspectAnnotation
    public int work() {
        return 0;
    }

}
