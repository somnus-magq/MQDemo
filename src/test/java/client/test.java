package client;

import com.magq.mq.client.MqClient;
import com.magq.mq.config.Config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试MQ
 */
public class test {

    private static final Logger logger = LoggerFactory.getLogger(test.class);

    //生产者生产MQ
    @Test
    public void testProduceClient() throws Exception{
        MqClient client = new MqClient();
        client.produce(Config.PRODUCE + "Hello World");
        System.out.println("SEND SUCCESS!");
        logger.info("SEND SUCCESS!");
    }

    //消费者消费MQ
    @Test
    public void testConsumeClient() throws Exception{
        MqClient client = new MqClient();
        String message = client.consume();
        logger.info("获取的消息为：" + message);
    }
}
