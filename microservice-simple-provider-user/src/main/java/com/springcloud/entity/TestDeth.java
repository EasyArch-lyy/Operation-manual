package com.springcloud.entity;

public class TestDeth {
    volatile static int i = 0;
    public static void main(String[] args) {
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        i++;
                        System.out.println(i);
                        Thread.sleep(2000);
                        while (true) {
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
