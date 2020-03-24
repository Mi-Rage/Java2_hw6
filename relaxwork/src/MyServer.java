
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    private static final int PORT = 8189;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен, ожидаем подключение...");
            Socket socket = server.accept();
            System.out.println(socket.getInetAddress().getHostAddress() + " Клиент подключен");
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            //В этом потоке бесконечно слушаем сокет.
            //Закрываем соединение если прочитали /end
            Thread thread = new Thread(() -> {
                while (true) {

                    String msg = "";
                    try {
                        msg = input.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (msg.equals("/end")) {
                        System.out.println("Соединение закрывается");
                        try {
                            output.writeUTF("/end");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                        break;
                    }
                    // Пометим что прочитали от клиента сообщение
                    System.out.println("От клиента: " + msg);
                    try {
                        //Пометим что сообщение принято сервером и передано далее
                        output.writeUTF("Через сервер: " + msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } );
            thread.start();

            // В этом потоке реализуем рассылку сообщений с сервера
            Thread thread1 = new Thread(() -> {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String broadcast = scanner.nextLine();
                    try {
                        output.writeUTF("Рассылка от сервера: " + broadcast);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread1.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}