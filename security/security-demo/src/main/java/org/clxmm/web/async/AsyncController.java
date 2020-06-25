package org.clxmm.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @author clx
 * @date 2020-06-25 20:08
 */
@RestController
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());


/*    @RequestMapping("/order")
    public Callable<String> order() {
        logger.info("主线程开始");

        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("副线程结束");
                return "async";
            }
        };
        logger.info("主线程结束");

        return result;
    }*/

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");

        String s = RandomStringUtils.randomNumeric(8);
        DeferredResult<String> result = new DeferredResult<>();
        mockQueue.setPlaceOrder(s);
        deferredResultHolder.getMap().put(s, result);

        logger.info("主线程结束");

        return result;
    }


}