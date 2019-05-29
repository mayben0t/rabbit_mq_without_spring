package work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class WorkQueueProducer {
    private static final String QUEUE_NAME = "queue_name_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
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
