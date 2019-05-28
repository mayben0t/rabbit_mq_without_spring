package consumer;

import com.rabbitmq.client.*;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

public class Consumer1 {
    private static final String QUEUE_NAME = "queue_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取channel
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        DeliverCallback deliverCallback = (consumerTag,delivery)->{
            String message = new String(delivery.getBody(),"UTF-8");
            System.out.println("[x] received:"+message);
        };

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});






        //
//        Consumer defaultConsumer = channel.getDefaultConsumer();

//        Consumer consumer = new DefaultConsumer(channel);
//
//        String s = channel.basicConsume(QUEUE_NAME, true, consumer);
////        consumer.handleCancel();
//        System.out.println();

    }


}
