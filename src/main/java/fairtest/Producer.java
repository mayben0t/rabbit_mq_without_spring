package fairtest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String QUEUE_NAME = "huawei";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.basicQos(1);
       for(int i=0;i<50;i++){
           String msg = "可能我也会想呢~~" + i;
           channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
           System.out.println(msg);
           Thread.sleep(i*20);
       }

        channel.close();
       connection.close();

//        channel.basicConsume(QUEUE_NAME,false,null);

    }
}
