/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jurest74
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            while (true) {
                sc = servidor.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String mensaje = in.readUTF();
                String[] mensajeArray = mensaje.split(",");
                int number1 = Integer.parseInt(mensajeArray[0]);
                int number2 = Integer.parseInt(mensajeArray[1]);
                String operacion = mensajeArray[2];

                System.out.println("Number 1 " + number1);
                System.out.println("Number 2 " + number2);
                System.out.println("Operacion " + operacion);
                int resp;
                String respuesta;
                switch (operacion) {
                   
                    case "sum":
                        System.out.println("Es una suma");
                        resp = number1 + number2;
                        respuesta = Integer.toString(resp);
                        out.writeUTF(respuesta);
                        break;
                    case "mul":
                        System.out.println("Es una multiplicacion");
                        resp = number1 * number2;
                        respuesta = Integer.toString(resp);
                        out.writeUTF(respuesta);
                        break;
                    case "div":
                        System.out.println("Es una resta");
                        resp = number1 / number2;
                        respuesta = Integer.toString(resp);
                        out.writeUTF(respuesta);
                        break;
                    case "rest":
                        System.out.println("Es una resta");
                        resp = number1 - number2;
                        respuesta = Integer.toString(resp);
                        out.writeUTF(respuesta);
                        break;
                    default:
                        System.out.println("Default");
                }

                sc.close();
                System.out.println("Cliente desconectado");
            }

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
