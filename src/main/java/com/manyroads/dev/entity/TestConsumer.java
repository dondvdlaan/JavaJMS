package com.manyroads.dev.entity;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import static com.manyroads.dev.constants.Constants.*;

/**
 * Class to start a session, create destination and consumer and receiving messages
 */
public class TestConsumer implements Runnable, ExceptionListener {

    public void run() {
        try {

            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(STR_URL_BROKER);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(STR_DESTINATION);

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println(STR_MSSG_CNSM + text);
            } else {
                System.out.println(STR_MSSG_CNSM + message);
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(STR_MSSG_ERROR_PROD + e);
            e.printStackTrace();
        }
    }
    public synchronized void onException(JMSException ex) {
        System.out.println(STR_MSSG_ERROR_CNSM);
    }
}