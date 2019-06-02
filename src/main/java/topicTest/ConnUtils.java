package topicTest;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnUtils {
    public static final String EXCHANGE_NAME = "topic_test";
    public static final String ROUTING_KEY_A = "CN.#";
    public static final String ROUTING_KEY_B = "CN.#";
    public static final String ROUTING_KEY_C = "US.#";
    public static final String QUEUE_A = "TOPIC_QUEUE_A";
    public static final String QUEUE_B = "TOPIC_QUEUE_B";



    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory.newConnection();
    }

}
