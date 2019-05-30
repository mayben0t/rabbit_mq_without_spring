package publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String EXCHANGE_NAME="可能我也会想呢(哭)";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        int i1 = 1;
        channel.basicQos(i1);

        for(int i=0;i<50;i++){
            String msg = "可能我也会想呢zzzzzz "+i;
            System.out.println("[Producer] send: " +msg);
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
            Thread.sleep(i*20);
//            Thread.sleep(TimeUnit.SECONDS.toSeconds(i*20/1000));
        }
//        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        channel.close();
        connection.close();
    }
}
