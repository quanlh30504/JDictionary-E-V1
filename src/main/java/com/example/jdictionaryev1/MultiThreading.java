package com.example.jdictionaryev1;

public class MultiThreading extends Thread {
    public void run(int seconds) throws InterruptedException {
        Thread.sleep(seconds);
    }
}
