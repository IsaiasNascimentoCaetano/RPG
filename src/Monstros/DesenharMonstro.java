package Monstros;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


//classe que manipula o desenho do monstro na tela 
public class DesenharMonstro {

    private BufferedImage esquerda[] = new BufferedImage[3];
    private BufferedImage direita[] = new BufferedImage[3];
    private int atual = 0; 
    private static final int d = 0;
    private static final int e = 1;
    
    public DesenharMonstro(){
    
        InputStream e = getClass().getResourceAsStream("/Arquivos/MonstroSprite/monstroEsquerda.png");
        InputStream d = getClass().getResourceAsStream("/Arquivos/MonstroSprite/monstroDireita.png");
        
        try{
        
            BufferedImage ime = ImageIO.read(e);
            BufferedImage imd = ImageIO.read(d);
        
            //Divide as imagens
            for(int a = 0; a < esquerda.length; a++){
            
                esquerda[a] = ime.getSubimage(a * 32, 0, 32, 32);
                direita[a] = imd.getSubimage(a * 32, 0, 32, 32);
            
            }
            
        }
        catch(IOException ex){
        
            System.err.println(ex);
        
        }
               
        iniciarThread();
        
    }
    
    private void iniciarThread(){
    
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            
                while(true){
                
                    atual++;
                                                            
                    try{
                    
                        Thread.sleep(1000 / 5);
                    
                    }
                    catch(InterruptedException ex){
                    
                        System.err.println(ex);
                    
                    }
                    
                    if(atual == 2){
                    
                        atual = 0;
                    
                    }
                    
                }
            
            }
        });
    
        t.start();
        
    }
    
    public void desenhar(Graphics2D g2, int x, int y, int posicao, Mapa.Mapa mapa){
     
        if(posicao == DesenharMonstro.d){
        
            g2.drawImage(direita[atual],(int)(mapa.getX() + x - 32 / 2), (int)(mapa.getY() + y - 32 / 2), null);
        
        }
        if(posicao == DesenharMonstro.e){
        
            g2.drawImage(esquerda[atual],(int)(mapa.getX() + x - 32 / 2), (int)(mapa.getY() + y - 32 / 2), null);
        
        }
        
    }
        
}
