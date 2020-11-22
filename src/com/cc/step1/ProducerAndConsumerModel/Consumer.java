package com.cc.step1.ProducerAndConsumerModel;

/**
 * Description 消费者模型
 * Author cloudr
 * Date 2020/11/22 19:56
 * Version 1.0
 **/
public class Consumer extends Thread {
    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int val = 0;
            try {
                val = container.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(val);
        }
    }
}
