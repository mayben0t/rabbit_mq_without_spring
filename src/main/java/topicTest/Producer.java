package topicTest;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ConnUtils.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);



        channel.basicQos(1);
        try {
            channel.txSelect();         //开启事务
            for (int i = 0; i < 50; i++) {
                String msg = "可能我也会想呢 " + i;
                String msg1 = "可能你也会想呢 " + i;
                channel.basicPublish(ConnUtils.EXCHANGE_NAME, "US.zz", null, msg.getBytes());
                channel.basicPublish(ConnUtils.EXCHANGE_NAME, "CN.azz", null, msg1.getBytes());
                System.out.println("has sent:" + msg);
            }
            channel.txCommit();         //提交事务
        } catch (Exception e) {
            channel.txRollback();       //事务回滚
        }
        channel.txCommit();
        channel.close();
        connection.close();


    }
}
