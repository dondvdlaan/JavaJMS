package com.manyroads.dev;

import com.manyroads.dev.entity.TestConsumer;
import com.manyroads.dev.entity.TestProducer;

/**
 * Simple Java Message Service example.
 */
public class JavaJMS {

    // *** Constants declaration ***

    // *** Main ***
    public static void main(String[] args) throws Exception {

        // Starting 2 Producers and 2 Consumers
        thread(new TestProducer(), false);
        thread(new TestProducer(), false);

        thread(new TestConsumer(), false);
        thread(new TestConsumer(), false);
    }

    // *** Methods ***
    /**
     * Method to start a thread
     *
     * @param "Runnable" runnable   : input paramater shall be of Runnable
     * @param "boolean" daemon      : toggle for enabling daemon thread
     */
    public static void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}
