package ru.gb.jtwo.lb.online;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    static class Creature implements Animal {
        @Override
        public void breathe() {

        }

        @Override
        public void look() {

        }
    }
    static class Booyan implements Bull {
        @Override
        public void voice() {

        }

        @Override
        public void walk() {

        }

        @Override
        public void breathe() {

        }

        @Override
        public void look() {

        }
    }
    static class Minotaur implements Bull, Human {

        @Override
        public void voice() {

        }

        @Override
        public void talk() {

        }

        @Override
        public void walk() {
            Bull.super.walk();
        }

        @Override
        public void breathe() {

        }

        @Override
        public void look() {

        }
    }
    static interface KeyboardListener {
        void keyPressed(String name);
    }
    static void addKeyboardListener(KeyboardListener l) {
        l.keyPressed("something");
    }
    private static void anonymousExample() {
        class MyClass implements KeyboardListener {
            @Override
            public void keyPressed(String name) {

            }
        }
        MyClass m = new MyClass();
        addKeyboardListener(m);
        addKeyboardListener(new MyClass());
        addKeyboardListener(new KeyboardListener() {
            @Override
            public void keyPressed(String name) {

            }
        });
        addKeyboardListener((name) -> {

        });
    }

    private static void complexMethod() throws IOException {
        //do something very complex
        throw new IOException("Hello from exceptions!");
        //everything else
    }


    private static class IOStream implements Closeable {
        FileNotFoundException fnfe = new FileNotFoundException("open fail");
        SQLException ioew = new SQLException("write fail");
        public IOStream() throws ClassNotFoundException {
            System.out.println("Here create an instance");
//            throw new ClassNotFoundException("What is IOStream?");
        }
        public void open() throws FileNotFoundException {
            System.out.println("open");
//            throw fnfe;
        }
        public void write() throws SQLException {
            System.out.println("write");
            throw ioew;
        }
        public void close() {
            System.out.println("close");
            throw new NullPointerException("nothing to close");
        }
    }

    public static void main(String[] args) {
        try (IOStream ios = new IOStream()) {
            ios.open();
            ios.write();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


        // 150 lines of code
//        try {
//            ios.write();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


//        try {
//            complexMethod();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

    }

    private static void tryExample() {
        IOStream ios = null;
        try {
            ios = new IOStream();
            ios.open();
            ios.write();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ios.close();
            } catch (NullPointerException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
