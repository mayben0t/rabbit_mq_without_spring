package producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String QUEUE_NAME = "queue_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
//        channel.basicPublish(QUEUE_NAME,"/",false,false,null,"zzzzzzzzzzzz".getBytes());
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("",QUEUE_NAME,null,"可能我也会想呢".getBytes());
        channel.close();
        connection.close();

    }

}
