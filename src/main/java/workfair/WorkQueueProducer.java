package workfair;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 使用公平分发
 * 消费者关闭自动应答
 * 消费者改为手动应答
 */

public class WorkQueueProducer {
    private static final String QUEUE_NAME = "queue_name_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        /**
         * 每个消费者发送确认消息前，消息队列不发送下一个消息到消费者，一次只处理一个消息
         *
         * 限制发送给同一个消费者不得超过一条消息
         */
        int i1 = 1;
        channel.basicQos(i1);
        
        for(int i=0;i<50;i++){
            String msg = "可能我也会想呢 "+i;
            System.out.println("[Producer] send: " +msg);
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            Thread.sleep(i*20);
//            Thread.sleep(TimeUnit.SECONDS.toSeconds(i*20/1000));
        }
        channel.close();
        connection.close();
    }

}
