package org.clxmm.web.service.impl;

import org.clxmm.web.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author clx
 * @date 2020-06-23 21:34
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello " + name;
    }
}
