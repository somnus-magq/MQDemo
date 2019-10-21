package com.magq.mq.server;


import com.magq.mq.config.Config;
import com.magq.mq.controller.Broker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 消息处理中心服务 BrokerServer
 * 用于启动消息处理中心
 */
public class BrokerServer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BrokerServer.class);

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                String str = in.readLine();
                if (str == null) {
                    continue;
                }
                logger.info("接收到原始数据：" + str);
                if (str.equals(Config.CONSUME)) {
                    //CONSUME 表示要消费一条消息
                    // 从消息队列中消费一条消息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                } else if (str.contains(Config.PRODUCE)) {
                    //接受到的请求包含SEND:字符串 表示生产消息放到消息队列中
                    Broker.produce(str);
                } else {
                    logger.info("原始数据:" + str + "没有遵循协议,不提供相关服务");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}