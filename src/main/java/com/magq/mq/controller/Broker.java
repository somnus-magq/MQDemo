package com.magq.mq.controller;

import com.magq.mq.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

import static com.google.common.collect.Queues.newArrayBlockingQueue;

/**
 * 消息处理中心
 */
public class Broker {
    private static final Logger logger = LoggerFactory.getLogger(Broker.class);

    // 保存消息数据的容器
    private static ArrayBlockingQueue messageQueue = newArrayBlockingQueue(Config.MAX_SIZE);

    // 生产消息
    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            logger.info("成功向消息处理中心投递消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            logger.info("消息处理中心内暂存的消息达到最大负荷，不能继续放入消息！");
        }
        logger.info("=======================");
    }

    // 消费消息
    public static String consume() {
        String msg = (String) messageQueue.poll();
        if (msg != null) {
            // 消费条件满足情况，从消息容器中取出一条消息
            logger.info("已经消费消息：" + msg + "，当前暂存的消息数量是：" + messageQueue.size());
        } else {
            logger.info("消息处理中心内没有消息可供消费！");
        }
        logger.info("=======================");
        return msg;
    }
}

