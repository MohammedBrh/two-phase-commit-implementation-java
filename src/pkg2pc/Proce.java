/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class Proce {

    public static void main(String args[]) {    
        try {
            Socket s = new Socket("localhost", 1996);

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String DemandeVote = "";
            DemandeVote = din.readUTF();
            System.out.println(DemandeVote);
            System.out.println("\nChioisir votre reponse :");
            System.out.println("Oui --> 1");
            System.out.println("Non --> 2");
            int flag = new Scanner(System.in).nextInt();
            if (flag == 1) {
                dout.writeUTF("OUI");
            } else {
                dout.writeUTF("NON");
            }
            String Reponse = "";
            Reponse = din.readUTF();
            System.out.println(Reponse);
            // AFFichage de reponse global 
            dout.close();
            s.close();
        } catch (IOException ex) {
            
            System.err.println("Erreur Connection !! ");
        }

    }

}
