/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Mohammed
 */
public class Client {

    public static void main(String args[]) {
        try {
            int a = 100;
            ServerSocket s = new ServerSocket(1969);
            Socket clientTT = s.accept();
            DataInputStream din = new DataInputStream(clientTT.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientTT.getOutputStream());
            dout.writeUTF("Client : Demande d'operation + 50 a le variable \"a\"");
            System.out.println("\n\t\tAtt la reponse de Codinateur !!");
            String REponse = din.readUTF();
            System.out.println(REponse);
            if (REponse.equals("Valide")) {
                a += 50;
                System.out.println("L'operatio est affectue !");
                System.out.println("a=" + a);
            } else {

                System.out.println("l'opération est Annuler !");
            }
        } catch (Exception e) {
            System.out.println("Erreur ");
        }
    }

}
