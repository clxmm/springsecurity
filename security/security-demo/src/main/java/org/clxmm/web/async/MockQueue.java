package org.clxmm.web.async;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author clx
 * @date 2020-06-25 20:35
 * 模拟消息队列
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 下单的消息
     */
    private String placeOrder;

    /**
     * 完成的消息
     */
    private String completeOrder;


    public String getPlaceOrder() {

        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            logger.info("接到下单请求" + placeOrder);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("下单请求处理完毕" + placeOrder);
            this.placeOrder = placeOrder;
            //给下单完成的消息赋值
            this.completeOrder = placeOrder;
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
