package com.example.testdrive.biz;

import com.example.testdrive.mq.MqHandler;
import com.example.testdrive.mq.MqListener;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 1/9/16
 */
public class MessageProcessor implements MqListener {

    private Communicator communicator;

    public MessageProcessor(Communicator communicator) {
        this.communicator = communicator;
    }

    private void bootstrap() {
        MqHandler mqHandler = new MqHandler(this);
        mqHandler.connect();
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Processing the message");
        this.communicator.sendMessage("123", "test-message");
    }
}
