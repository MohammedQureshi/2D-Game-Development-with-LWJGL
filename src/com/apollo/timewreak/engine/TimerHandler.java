package com.apollo.timewreak.engine;

public class TimerHandler {
    public static double getTime(){
        return (double)System.nanoTime() / (double)1000000000L;
    }
}
