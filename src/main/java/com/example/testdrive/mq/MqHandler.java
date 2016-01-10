package com.example.testdrive.mq;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 1/9/16
 */
public class MqHandler {

    private Boolean isNewMessage = false;
    private MqListener listener;

    public MqHandler(MqListener listener) {
        this.listener = listener;
    }

    public void connect() {
        System.out.println("Connecting to MQ");
        System.out.println("Connected to MQ");
    }

    public void send(String queueName, String message) {
        System.out.println("Sending message to queue: " + queueName);
        System.out.println("Message sent");
    }

    public void listen(String queueName) {
        if (isNewMessage) {
            this.listener.onMessage("new message");
        }
    }
}
