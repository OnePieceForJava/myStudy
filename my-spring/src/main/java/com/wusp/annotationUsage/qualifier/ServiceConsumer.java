package com.wusp.annotationUsage.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceConsumer {

    @Autowired
    private IService iService;

    public void call() {
        iService.print();
    }

}
