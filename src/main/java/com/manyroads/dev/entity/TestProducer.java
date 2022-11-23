package com.manyroads.dev.entity;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.manyroads.dev.constants.Constants.*;

/**
 * Class to start a session, create destination and producer and send a message
 */
public class TestProducer implements Runnable {

    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(STR_URL_BROKER);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(STR_DESTINATION);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            String text = STR_MSSG_PROD + Thread.currentThread().getName() + STR_COLON + this.hashCode();
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            System.out.println(STR_MSSG_PROD_CMD + message.hashCode() + STR_COLON + Thread.currentThread().getName());
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(STR_MSSG_ERROR_PROD + e);
            e.printStackTrace();
        }
    }
}
