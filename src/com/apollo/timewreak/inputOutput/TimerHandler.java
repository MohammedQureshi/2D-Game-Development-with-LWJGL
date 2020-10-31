package com.apollo.timewreak.inputOutput;

public class TimerHandler {
    public static double getTime(){
        return (double)System.nanoTime() / (double)1000000000L;
    }
}
