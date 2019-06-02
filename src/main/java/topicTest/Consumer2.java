package topicTest;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(ConnUtils.QUEUE_B,false,false,false,null);
        channel.queueBind(ConnUtils.QUEUE_B,ConnUtils.EXCHANGE_NAME,ConnUtils.ROUTING_KEY_C);
//        channel.queueBind(ConnUtils.QUEUE_A,ConnUtils.EXCHANGE_NAME,ConnUtils.ROUTING_KEY_B);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                try {

                    System.out.println("[queue_B] recv:"+new String(body,"UTF-8"));

                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };


        channel.basicConsume(ConnUtils.QUEUE_B,false,consumer);
    }
}
