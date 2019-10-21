package com.magq.mq.MQStart;

import com.magq.mq.config.Config;
import com.magq.mq.server.BrokerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;

/**
 * 启动加载MQ
 */

@Component
public class loadMq implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(loadMq.class);

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("启动加载MQ  -- START");
        try {
            ServerSocket server = new ServerSocket(Config.SERVICE_PORT);
            while (true) {
                BrokerServer brokerServer = new BrokerServer(server.accept());
                new Thread(brokerServer).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
