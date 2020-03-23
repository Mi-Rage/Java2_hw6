package ru.gb.jtwo.le.online;

public class Main {
    private static long a;
    private static long b;
    private static long c;

    private static synchronized void incAllVars() {
        for (int i = 0; i < 1000000; i++) {
            a = a + 1;
            b = b + 1;
            c = c + 1;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a, b, c);
        System.out.println(vars);
    }

    public static void main(String[] args) {
        Runnable r = () -> {
            // a lot of code here
            incAllVars();
            // a lot of code here
        };

        new Thread(r, "t0").start();
        new Thread(r, "t1").start();
        new Thread(r, "t2").start();
    }

    private static void joinExample() {
        // big img
        // split img
        MyThread mt1 = new MyThread("MyThread-0", 1);
        MyThread mt2 = new MyThread("MyThread-1", 2);
        MyThread mt3 = new MyThread("MyThread-2", 3);

        try {
            mt2.join();
            System.out.println("Main after two");
            mt1.join();
            System.out.println("Main after one");
            mt3.join();
            System.out.println("Main after three");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        // concatenate

//        new Thread(() -> {
//            System.out.println("Hi");
//            }
//        ).start();
    }
}
