package Musica;

import javazoom.jl.player.*;
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;

public class Musicas {

    private InputStream musica;
    private Player player;
    private boolean tocar;

    //Toca um loop
    public Musicas(final String local,boolean tocars) {

        musica = null;
        player = null;
        
        tocar = tocars;
        
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    while (tocar) {
                        
                        musica = getClass().getResourceAsStream(local);
                        player = new Player(musica);
                        player.play();
                                                
                    }
                                     
                    
                } catch (JavaLayerException e) {

                    System.err.println(e);

                }

            }
        });

        t.start();

    }
    
    //Toca um som apenas uma vez
    public Musicas(final String local){
    
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            
                try{
                
                    musica = getClass().getResourceAsStream(local);
                    player = new Player(musica);
                    player.play();
                    
                }
                catch(JavaLayerException e){
                
                    System.err.println(e);
                
                }
            
            }
        });
    
        t.start();
        
    }

    public void parar() {

        //Espera para a música parar
        try{
        
            Thread.sleep(600);
        
        }
        catch(InterruptedException e){
        
            System.err.println(e);
        
        }
        
        tocar = false;
        player.close();
                
    }
}