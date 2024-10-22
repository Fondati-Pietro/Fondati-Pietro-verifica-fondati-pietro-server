package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class MioThread extends Thread{
    Socket s;

    MioThread(Socket s){
        this.s = s;

    try {

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());          

            String numeroRicevuto;
            String cambio;

            Random random = new Random();
            int numeroRandom = random.nextInt(100);

            do {
            
                    numeroRicevuto = in.readLine();

                    if(Integer.parseInt(numeroRicevuto) < numeroRandom){
                        out.writeBytes( "<" + '\n');
                    }

                    if(Integer.parseInt(numeroRicevuto) > numeroRandom){
                        out.writeBytes( ">" + '\n');
                    }

                    if(Integer.parseInt(numeroRicevuto) == numeroRandom){
                        out.writeBytes( "=" + '\n');
                    }
                    
                    if(Integer.parseInt(numeroRicevuto) >= 100){
                        out.writeBytes( "!" + '\n');
                    }

                    if(Integer.parseInt(numeroRicevuto) < 0){
                        out.writeBytes( "!!" + '\n');
                    }

                    if (numeroRicevuto.equals("esc")) {
                        cambio = "esc";
                        out.writeBytes( cambio + '\n');
                    }

                } while (!numeroRicevuto.equals("esc"));

                this.s.close();
                out.close();
                in.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }
}
