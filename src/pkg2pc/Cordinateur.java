/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2pc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static pkg2pc.Cordinateur.compteurReponseOui;

/**
 *
 * @author Mohammed
 */
public class Cordinateur extends Thread {

    static int compteurReponseOui = 0;
    String Reponse = "";
    public static ArrayList<Socket> PRoc;
    static DataInputStream din1;
    static DataOutputStream dout1;

    public static void main(String[] args) throws IOException {
        System.out.println("-----------------------------------------------------");
        System.out.println("---------------- Serveur est demareé ----------------");

        Socket sT = new Socket("localhost", 1969);

        din1 = new DataInputStream(sT.getInputStream());
        dout1 = new DataOutputStream(sT.getOutputStream());

        String dem = din1.readUTF();
        System.out.println(dem);

        ///---------------------------
        ServerSocket ss = new ServerSocket(1996);
        PRoc = new ArrayList<Socket>();
        while (true) {
            Socket client = ss.accept();

            PRoc.add(client);
            ClientHandler ch = new ClientHandler(client);
            ch.start();

        }

    }

    static void ReponseGlobale(boolean rep) throws IOException {
        if (rep) {
            System.out.println("Decision Gloale : Valider ! ");
            for (int i = 0; i < PRoc.size(); i++) {

                DataOutputStream dout = new DataOutputStream(PRoc.get(i).getOutputStream());
                dout.writeUTF("Cordinateur : Valider !");
            }
            dout1.writeUTF("Valide");

        } else {
            //reposne Global est non
            System.out.println("Decision Gloale : Annuler ! ");
            for (int i = 0; i < PRoc.size(); i++) {

                DataOutputStream dout = new DataOutputStream(PRoc.get(i).getOutputStream());
                dout.writeUTF("Cordinateur : Annuler  !");
            }
            dout1.writeUTF("NON-Valide");
        }
        
            System.out.println("\t\tj'ai envoyé la Decision globale");
    }

}

class ClientHandler extends Thread {

    int i = 0;
    Socket s = null;
    DataInputStream din;
    DataOutputStream dout;

    public ClientHandler(Socket s) {
        this.s = s;

    }

    public void run() {
        try {
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("---------- Cordinateur : votez SVP (Oui | Non) ----------");
            System.out.println("-----------------------------------------------------");
            System.out.println("\t\tj'ai envoyé la demande de vote");
            System.out.println("\n\t\tAtt La Reponse de Client !! \n");

            String REponse;
            s.setSoTimeout(15000);
            try {
                REponse = din.readUTF();
                System.out.println("Reponse du Client :" + REponse);

                if (REponse.equals("OUI")) {
                    if (compteurReponseOui == 1) {
                        Cordinateur.ReponseGlobale(true);
                    } else {
                        compteurReponseOui++;
                        System.out.println("nous attendons les autres reposes !");
                    }
                } else {
                    Cordinateur.ReponseGlobale(false);
                }

            } catch (Exception ex) {
                System.out.println("un processus ne repond pas dans le delais de garde !!");
                Cordinateur.ReponseGlobale(false);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
