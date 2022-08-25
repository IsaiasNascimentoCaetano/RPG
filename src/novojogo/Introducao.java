package novojogo;

import MenuInicial.MenuInial;
import Musica.Musicas;
import Painel.Painel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Introducao extends JPanel implements KeyListener{

    //Variáveis para desenhar no painel
    private BufferedImage imagem;
    private BufferedImage []imagens = new BufferedImage[2];
    private Graphics2D grafico;
    private boolean rodar;
    private boolean clicar;
    private boolean letras;
    private int acontecimentos;
    private int proximaLetra;    
    private Musicas intro;
        
    public Introducao(){
    
        super();
        
        //Cria o painel, com a largura e a altura
        setPreferredSize(new Dimension(Painel.largura, Painel.altura));     
        //Permite que o usuário possa usar teclas
        setFocusable(true);
        //Adiciona o foco na tela, assim podemos capturar os cliques do usuário
        requestFocus();
        addKeyListener(this);
        
        intro = new Musicas("/Musicas/intro.mp3",true);
        
        iniciar();
        
    }
    
    private void iniciar(){
        
        imagem = new BufferedImage(Painel.largura, Painel.altura, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) imagem.getGraphics();
        acontecimentos = 1;        
        rodar = true;
        
        loop();
        carregar();
        
    }
    
    private void loop(){
    
        Thread t = new Thread(new Runnable(){

            @Override
            public void run() {
            
                while(rodar){
                
                    desenhar(grafico);
                    desenharNaTela();
                    
                    try{
                    
                        Thread.sleep(1000 / 60);
                        
                    }
                    catch(InterruptedException ex){
                    
                        System.err.println(ex);
                    
                    }
                
                }
            
            }
        
        });
    
        t.start();
        
    }
    
    private void carregar(){
    
        Thread timer = new Thread(new Runnable() {

            @Override
            public void run() {
            
                try{
                
                    //Nomes
                    Thread.sleep(3000);
                    acontecimentos++;
                    
                    Thread.sleep(4000);
                    acontecimentos++;
                    
                    Thread.sleep(4000);
                    acontecimentos++;
                    
                    //Aprensenta
                    
                    InputStream s = getClass().getResourceAsStream("/Arquivos/Introducao/ici.png");
                    imagens[0] = ImageIO.read(s);
                    s = null;
                    
                    Thread.sleep(5000);
                    acontecimentos++;
                                                        
                    //titulo
                    s = getClass().getResourceAsStream("/Arquivos/Introducao/logo.png");
                    imagens[1] = ImageIO.read(s);
                                        
                    //Habilita as letras que mudam
                    letras = true;
                    
                    //inicia a thread das letras
                    ThreadLetra();
                   
                    Thread.sleep(1500);
                    //Habilita a opção de clicar
                    clicar = true;
                    
                                        
                }
                catch(InterruptedException | IOException ex){
                
                    System.err.println(ex);
                
                }
                                            
            }
            
        });
        
        timer.start();
    }
        
    private void desenhar(Graphics2D g2){
    
        g2.clearRect(0, 0, Painel.largura, Painel.altura);
        
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Painel.largura, Painel.altura);
        
        if(acontecimentos == 1){
        
            g2.setColor(new Color(255, 140, 105));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Programador", Painel.largura / 2 - 50 , Painel.altura / 2);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Isaias", Painel.largura / 2 - 50 , Painel.altura / 2 + 50);
            
        
        }
        
        if(acontecimentos == 2){
        
            g2.setColor(new Color(255, 140, 105));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Programador", Painel.largura / 2 - 50 , Painel.altura / 4);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Isaias", Painel.largura / 2 - 50 , Painel.altura / 4 + 50);
         
            g2.setColor(new Color(255, 127, 0));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Designer", Painel.largura / 2 - 50 , Painel.altura / 2);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Isabela", Painel.largura / 2 - 50 , Painel.altura / 2 + 50);
            
        }
        
        if(acontecimentos == 3){
        
            g2.setColor(new Color(255, 140, 105));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Programador", Painel.largura / 2 - 50 , Painel.altura / 4);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Isaias", Painel.largura / 2 - 50 , Painel.altura / 4 + 50);
         
            g2.setColor(new Color(255, 127, 0));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Designer", Painel.largura / 2 - 50 , Painel.altura / 2);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Isabela", Painel.largura / 2 - 50 , Painel.altura / 2 + 50);
            
            g2.setColor(new Color(255, 69, 0));
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Roterista", Painel.largura / 2 - 50 , Painel.altura / 4 * 3);
            
            g2.setFont(new Font("Arial", Font.PLAIN, 45));
            g2.drawString("Caio", Painel.largura / 2 - 50 , Painel.altura / 4 * 3 + 50);
        
        }
        
        if(acontecimentos == 4){
        
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            
            g2.drawImage(imagens[0],30, 50, null);
            
            g2.setColor(new Color(176, 224, 230));
            g2.drawString("ICI Games apresenta:",30,100);
        
        }
        if(acontecimentos == 5){
        
            g2.drawImage(imagens[1],75,75,null);
        
            if(proximaLetra == 0){
            
                g2.setFont(new Font("Arial", Font.PLAIN, 30));
                g2.setColor(new Color(238, 221, 130));
                g2.drawString("Pressione enter", Painel.largura / 2 - 110, 400);
                                            
            }
            if(proximaLetra == 2){
            
                g2.setFont(new Font("Arial", Font.PLAIN, 30));
                g2.setColor(new Color(205, 102, 0));
                g2.drawString("Pressione enter", Painel.largura / 2 - 110, 400);
            
            }
                                 
        }
    
    }
    
    private void ThreadLetra(){
    
        Thread t = new Thread(new Runnable(){
        
            public void run(){
            
                while(letras){
                
                    try{
                    
                        proximaLetra = 0;
                        Thread.sleep(700);
                        proximaLetra = 1;
                        Thread.sleep(400);
                        proximaLetra = 2;
                        Thread.sleep(700);
                        proximaLetra = 1;
                        Thread.sleep(400);
                                
                    }
                    catch(InterruptedException ex){
                    
                        System.err.println(ex);
                    
                    }
                
                }
            
            }
        
        });
        
        t.start();
        
    }
    
    private void desenharNaTela(){
    
        //Aqui as vezes ocorre um erro desconhecido
        try{
        
            Graphics g = this.getGraphics();
            g.drawImage(imagem, 0, 0, null);
            g.dispose();
        
        }
        catch(NullPointerException e){}
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(clicar && ke.getKeyCode() == KeyEvent.VK_ENTER){
                        
            intro.parar();
            NovoJogo.f.dispose();
            MenuInial menu = new MenuInial();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
                    
            rodar = false;
            letras = false;
                    
        }
    
    }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
}
