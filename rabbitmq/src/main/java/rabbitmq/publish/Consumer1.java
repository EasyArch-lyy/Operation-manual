package rabbitmq.publish;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import rabbitmq.ConnectionUtil;

import java.io.IOException;

/**
 * @author lyy
 */
public class Consumer1 {

    private static final String QUEUE_NAME = "test_queue_exchange_1";

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        /*
            绑定队列到交换机（这个交换机名称一定要和生产者的交换机名相同）
            参数1：队列名
            参数2：交换机名
            参数3：Routing key 路由键
         */
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //同一时刻服务器只会发一条数据给消费者
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
                throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body,"UTF-8");
                System.out.println("收到消息："+message);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}