package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String host = "server"; // Dirección del servidor
        Socket socket = null; // Socket para la comunicación
        ObjectOutputStream oos = null; // Stream de salida de objetos
        ObjectInputStream ois = null; // Stream de entrada de objetos

        // Bucle para enviar y recibir solicitudes al servidor
        for (int i = 0; i < 5; i++) {
            // Establecer una conexión con el servidor
            socket = new Socket(host, 9876);

            // Obtener el stream de salida de objetos del socket
            oos = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Sending request to Socket Server");

            // Enviar una solicitud al servidor
            if (i == 4) {
                oos.writeObject("exit");
            } else {
                oos.writeObject("" + i);
            }

            // Obtener el stream de entrada de objetos del socket
            ois = new ObjectInputStream(socket.getInputStream());

            // Leer la respuesta del servidor
            String message = (String) ois.readObject();

            System.out.println("Message: " + message);

            // Cerrar los streams y el socket
            ois.close();
            oos.close();
            socket.close();

            // Pausar el hilo actual durante 100 milisegundos
            Thread.sleep(100);
        }
    }
}
