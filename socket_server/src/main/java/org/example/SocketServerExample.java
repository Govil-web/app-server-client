package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerExample {
    private static ServerSocket server; // Socket del servidor
    private static int port = 9876; // Puerto de escucha del servidor

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port); // Crear un ServerSocket en el puerto especificado

        // Bucle para esperar solicitudes de clientes
        while (true) {
            System.out.println("Esperando respuesta del cliente");

            // Aceptar una conexión entrante del cliente
            Socket socket = server.accept();

            // Obtener el stream de entrada de objetos del socket
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // Leer el mensaje enviado por el cliente
            String message = (String) ois.readObject();

            System.out.println("Mensaje recibido: " + message);

            // Obtener el stream de salida de objetos del socket
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            // Enviar una respuesta al cliente
            oos.writeObject("Hola desde el servidor " + message);

            // Cerrar los streams y el socket
            ois.close();
            oos.close();
            socket.close();

            // Comprobar si el mensaje es "exit" para finalizar el servidor
            if (message.equalsIgnoreCase("exit"))
                break;
        }

        System.out.println("Cerrando Socket server!!");
        server.close();
    }

}