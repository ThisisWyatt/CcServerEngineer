package com.cc.step1.ProducerAndConsumerModel;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Description 生产者消费者模型中的容器
 * Author cloudr
 * Date 2020/11/22 19:46
 * Version 1.0
 **/
public class Container {
    private Queue<Integer> container = new LinkedList<>();
    private int containerSize = 5;  //容器的大小

    /**
    * @Description: 生产者生产内容
    * @Param: [val] 添加的内容
    * @return: void
    * @Author: cloudr
    * @Date: 2020/11/22
    */
    public synchronized void add(int val) throws InterruptedException {
        if (container.size() > containerSize) {
            wait(); //阻塞生产者，不让其继续生产
        }
        container.add(val);
        notify();   //通知消费者继续消费
    }

    /**
    * @Description: 消费者消费内容
    * @Param: []
    * @return: int 返回生产者所生产的
    * @Author: cloudr
    * @Date: 2020/11/22
    */
    public synchronized int get() throws InterruptedException {
        if (container.size() == 0) {
            wait(); //阻塞生消费者，不让其继续消费
        }
        int returnRes = container.poll();
        notify();   //通知生产者继续生产
        return returnRes;
    }
}
