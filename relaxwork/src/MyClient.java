import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    Scanner scanner = new Scanner(System.in);

    private String msgString;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public MyClient() {
        try {
            System.out.println("Подключение к серверу...");
            openConnection();
        } catch (IOException e) {
            System.out.println("Нет связи с сервером");
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Подключено!");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        //В этом потоке бесконечно отсылаем если есть что слать в сокет
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    msgString = scanner.nextLine();
                    if (!msgString.trim().isEmpty()) {
                        try {
                            out.writeUTF(msgString);
                        } catch (IOException e) {
                            System.out.println("Ошибка отправки . Нет связи\n" + e);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                closeConnection();
                e.printStackTrace();
            }
        });
        thread.start();

        // В этом потоке бесконечно читаем сокет
        //Закрываем соединение если мы или сервер прислал /end
        Thread thread1 = new Thread(() -> {
                try {
                    while (true) {
                        String broadcast = in.readUTF();
                        if (broadcast.equals("/end")) {
                        closeConnection();
                        System.exit(0);
                        break;
                    }
                        System.out.println(broadcast);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        });
        thread1.start();

    }


    //Закрываем все соединения
    public void closeConnection() {
        System.out.println("Соединение закрывается");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new MyClient();
    }


}
