package com.example.testdrive.biz;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 1/9/16
 */
public interface Communicator {
    void sendMessage(String number, String message);

    void receiveMessage(String number);
}
