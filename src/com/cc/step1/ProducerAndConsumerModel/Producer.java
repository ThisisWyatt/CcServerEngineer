package com.cc.step1.ProducerAndConsumerModel;

/**
 * Description 生产者模型
 * Author cloudr
 * Date 2020/11/22 19:53
 * Version 1.0
 **/
public class Producer extends Thread{
    private Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                container.add(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
