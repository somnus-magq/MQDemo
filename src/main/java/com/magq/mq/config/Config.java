package com.magq.mq.config;

/**
 * MQ配置
 */
public class Config {
    // 队列存储消息的最大数量
    public final static int MAX_SIZE = 3;

    //MQ端口号
    public static final int SERVICE_PORT = 9999;

    //消费MQ消费者请求
    public final static String CONSUME = "CONSUME";

    //生产者生产MQ请求
    public final static String PRODUCE = "SEND:";

    //public final static String localHost = InetAddress.getLocalHost();

}
