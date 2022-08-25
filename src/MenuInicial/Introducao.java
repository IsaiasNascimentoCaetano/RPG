package MenuInicial;

import Painel.Painel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Introducao extends JPanel{

    private String mensagens[];
    private final int x = 10;
    private int y;
    private BufferedImage im;
    private Graphics2D grafico;
    private boolean rodar;
    private BufferedImage pergaminho;
    
    public Introducao(){
    
        super();
        
        mensagens = new String[10];
        
        setPreferredSize(new Dimension(Painel.largura, Painel.altura));
        setFocusable(true);
        requestFocus();
                  
        y = Painel.altura - 20;
        
        escreverHistoria();
        
        im = new BufferedImage(Painel.largura, Painel.altura, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) im.getGraphics();
    
        try
        {
        
            InputStream p = getClass().getResourceAsStream("/Arquivos/pergaminho.png");
            pergaminho = ImageIO.read(p);
        
        }
        catch(IOException e){
        
            System.err.println(e);
        
        }
        
        rodar = true;
        
        thread();
        
    }
    
    private void thread(){
    
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            
                while(rodar){
                
                    try{
                              
                        Desenha();
                        Thread.sleep(15);
                        
                        if(y == 35){
                        
                            y = 35;
                            
                            try{
                            
                                Thread.sleep(1000);
                                
                            }
                            catch(InterruptedException e){
                            
                                System.err.println(e);
                            
                            }
                        
                            y = 1;
                            
                        }
                        else if(y != 35 && y != 1){
                        
                            y -= 1;
                        
                        }
                        
                    
                    }
                    catch(InterruptedException e){
                
                        System.err.println(e);
                
                    }
            
                }
                
            }
        });
        
        t.start();
    
    }
    
    private void Desenha(){
        
        try{
             
            Graphics g = this.getGraphics();    
            
            grafico.setColor(Color.BLACK);
            grafico.fillRect(0, 0, Painel.largura, Painel.altura);
            
            if(y != 1){
            
                grafico.drawImage(pergaminho,x - 100,y - 50, Painel.largura + 160, Painel.altura,null);
                
                grafico.setColor(Color.BLACK);
                
                for(int a = 0; a < mensagens.length; a++){
            
                    grafico.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 30));
                    grafico.drawString(mensagens[a], x, y + (35 * a) + 70 );
            
                }
                       
            }
            else{
            
                grafico.setColor(new Color(102, 205, 170));
                grafico.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 60));
                grafico.drawString("Game", x, Painel.altura / 2);
            
                try{
                
                    Thread.sleep(1000);
                    
                }
                catch(InterruptedException e){
                
                    System.err.println(e);
                
                }
                
                rodar = false;
                iniciarJogo();
                
            }
            
            g.drawImage(im, 0, 0, null);
            
            g.dispose();
                                
        }
        catch(NullPointerException e){}
       
    }
    
    private void escreverHistoria(){
    
        mensagens[0] = "Ano de 1363,";
        mensagens[1] = "um jovem acorda e percebe que ";
        mensagens[2] = "sua casa está estranha.";
        mensagens[3] = "Assustado, ele sai de sua casa,";
        mensagens[4] = "e ao sair ele percebe que sua";
        mensagens[5] = "aldeia foi devastada.";
        mensagens[6] = "Então ele pega a espada que";
        mensagens[7] = "está em sua família há gerações,";
        mensagens[8] = "e vai até o castelo de";
        mensagens[9] = "s’Alohrrenn.";   
                
    }
    
    private void iniciarJogo(){
    
        JFrame tela = new JFrame("Teste");          
        Painel painel = new Painel();
        tela.add(painel);        
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setResizable(false);        
        tela.pack();
        tela.setLocationRelativeTo(null);                
        tela.setVisible(true);
    
        TelaNome.tela.setVisible(false);
        
    }
    
}
