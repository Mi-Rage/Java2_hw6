package ru.gb.jtwo.le.online;

public class MyThread extends Thread {
    private int multiple;

    public MyThread(String name, int multiple) {
        super(name);
        this.multiple = multiple;
        start();
    }

    @Override
    public void run() {
        System.out.println(getName() + " started");
        for (long i = 0; i < (multiple * 10000000000L); i++) {
            long a = i * 432;
        }
        System.out.println(getName() + " stopped");
    }
}
