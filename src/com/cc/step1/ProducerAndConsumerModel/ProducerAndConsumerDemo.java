package com.cc.step1.ProducerAndConsumerModel;

/**
 * Description 生产者消费者模型
 * Author cloudr
 * Date 2020/11/22 19:59
 * Version 1.0
 **/
public class ProducerAndConsumerDemo {
    public static void main(String[] args) {
        Container container = new Container();
        Producer producer = new Producer(container);
        Consumer consumer = new Consumer(container);
        producer.start();
        consumer.start();
    }
}
