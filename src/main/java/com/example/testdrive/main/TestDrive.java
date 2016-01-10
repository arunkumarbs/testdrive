package com.example.testdrive.main;

import com.example.testdrive.biz.Communicator;
import com.example.testdrive.biz.MessageProcessor;
import com.example.testdrive.biz.WhatsAppProcessor;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 1/9/16
 */
public class TestDrive {

    private void bootstrap() {
        Communicator communicator = new WhatsAppProcessor();
        new MessageProcessor(communicator);
    }

    public static void main(String[] args) {
        TestDrive testDrive = new TestDrive();
        testDrive.bootstrap();
    }
}
