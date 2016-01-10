package com.example.testdrive.mq;

/**
 * @author arunkumar_balasubramanian
 * @version 1.0 1/9/16
 */
public interface MqListener {
    void onMessage(String message);
}
