/*
* @author Isaias Nascimento Caetano Pinto
*/

package Painel;

import Fases.Gerenciador;
import Salvar.Salvar;
import Salvar.StatusSalvar;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class Painel extends JPanel implements KeyListener{
    
    //Constantes com a largura e a altura da tela
    public static final int altura = 500;
    public static final int largura = 600;

    //Variáveis usadas no loop do jogo
    private Thread loop;
    private long FPS = 1000/60;
    private static boolean rodar;
    
    //Variáveis para desenhar no painel
    private BufferedImage imagem;
    private Graphics2D grafico;
    
    //Objeto que gerencia as fases
    private Gerenciador gerenciador;
    private Salvar s;
    
    /*Construtor:
    *Esse construtor inicia o que é necessário
    *Por último ele chama o método iniciar.
    */
    public Painel(){
    
        super();
        
        //Cria o painel, com a largura e a altura
        setPreferredSize(new Dimension(Painel.largura, Painel.altura));     
        //Permite que o usuário possa usar teclas
        setFocusable(true);
        //Adiciona o foco na tela, assim podemos capturar os cliques do usuário
        requestFocus();
        //adiciona os eventos do teclado
        addKeyListener(this);
        
        iniciar();
        
    }
    
    public Painel(boolean carregar){
    
        super();
        
        //Cria o painel, com a largura e a altura
        setPreferredSize(new Dimension(Painel.largura, Painel.altura));     
        //Permite que o usuário possa usar teclas
        setFocusable(true);
        //Adiciona o foco na tela, assim podemos capturar os cliques do usuário
        requestFocus();
        //adiciona os eventos do teclado
        addKeyListener(this);
        
        carregarUltimaPartida();
    
    }
    
    /*    
    *Este método inicia todos os objetos que serão usados durante o jogo,
    *e inicia a Thread após todos esses objetos terem sido criados
    */
    private void iniciar(){
        
        imagem = new BufferedImage(Painel.largura, Painel.altura, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) imagem.getGraphics();
        
        //Cria uma partida nova
        gerenciador = new Gerenciador();
        
        //Torna rodar positivo, para iniciar a Thread
        Painel.rodar = true;
        
        iniciarLoop();
        
    }
    
    /*
    Este método carrega o jogo salvo
    */
    private void carregarUltimaPartida(){
    
        imagem = new BufferedImage(Painel.largura, Painel.altura, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) imagem.getGraphics();
    
        //Carrega
        s = new Salvar();
        gerenciador = new Gerenciador(s);
        carregar();
        gerenciador.conferirMapa();
        
        //Torna rodar positivo, para iniciar a Thread
        Painel.rodar = true;
        
        iniciarLoop();
        
    }    
    
    /*
    *Este método gerencia todo o loop do jogo.
    *Nele é criada uma Thread, onde fica um while(true)
    *atualizando a tela a um determinado espaço de tempo.(FPS)
    *Ele chama 3 métodos para atualizar a tela.
    */
    private void iniciarLoop(){
    
        loop = new Thread(new Runnable(){
        
            @Override
            public void run(){
            
                while(rodar){
                    
                    long inicio;
                    long elapse;
                    long wait;
                    
                    try{
                    
                        inicio = System.nanoTime();
                                                
                        atualizar();
                        desenhar();
                        desenharNaTela();
                        
                        elapse = System.nanoTime() - inicio;
                                               
                        wait = FPS - elapse / 1000000;
                                                
                        if(wait < 0){
                        
                            wait = 12;
                        
                        }
                        
                        Thread.sleep(wait);
                    
                    }
                    catch(InterruptedException e){
                    
                        System.err.println(e);
                    
                    }
                    
                }            
            
            }
        
        });
    
        loop.start();
        
    }
    
    
    //Este método atualiza as imagens e outras coisas no jogo
    private void atualizar(){
    
        gerenciador.update();
    
    }
       
    //Este método desenha tudo    
    private void desenhar() {
    
        grafico.clearRect(0, 0, Painel.largura, 150);
        
        //Desenha um fundo preto na tela
        grafico.setColor(Color.black);
        grafico.fillRect(0, 0, Painel.largura, Painel.altura);
        
        //Desenha o mapa
        gerenciador.draw(grafico);        
        
        if(StatusSalvar.isTelaStatus() && !Gerenciador.isMoverPersonagem()){
        
            StatusSalvar.draw(grafico);
         
        }
        
    }
    
    /*
    *Este método coloca na tela tudo o que o 
    *que o método anterior desenhou
     */
    private void desenharNaTela(){
    
        //Aqui as vezes ocorre um erro desconhecido
        try{
        
            Graphics g = this.getGraphics();
            g.drawImage(imagem, 0, 0, null);
            g.dispose();
        
        }
        catch(NullPointerException e){}
        
    }

    //Eventos do teclado
    @Override
    public void keyTyped(KeyEvent ke) {}

    //quando o teclado é pressionado
    @Override
    public void keyPressed(KeyEvent ke) {
    
        if(gerenciador.isMoverPersonagem()){
         
            if(ke.getKeyCode() == KeyEvent.VK_W){
            
                Gerenciador.getHeroi().setCima(true);
                Gerenciador.getHeroi().setBaixo(false);
                Gerenciador.getHeroi().setEsquerda(false);
                Gerenciador.getHeroi().setDireita(false);
                
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
                
                Gerenciador.getHeroi().setDx(0);
                
            }
            
            if(ke.getKeyCode() == KeyEvent.VK_S){
            
                Gerenciador.getHeroi().setCima(false);
                Gerenciador.getHeroi().setBaixo(true);
                Gerenciador.getHeroi().setEsquerda(false);
                Gerenciador.getHeroi().setDireita(false);
                
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
            
                Gerenciador.getHeroi().setDx(0);
                
            }
            
            if(ke.getKeyCode()== KeyEvent.VK_A){
            
                Gerenciador.getHeroi().setCima(false);
                Gerenciador.getHeroi().setBaixo(false);
                Gerenciador.getHeroi().setEsquerda(true);
                Gerenciador.getHeroi().setDireita(false);
                
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
            
                Gerenciador.getHeroi().setDy(0);
                
            }
            
            if(ke.getKeyCode() == KeyEvent.VK_D){
            
                Gerenciador.getHeroi().setCima(false);
                Gerenciador.getHeroi().setBaixo(false);
                Gerenciador.getHeroi().setEsquerda(false);
                Gerenciador.getHeroi().setDireita(true);
            
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
                
                Gerenciador.getHeroi().setDy(0);
                
            }
            
            if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            
                Gerenciador.setMoverPersonagem(false);
                StatusSalvar.setTelaStatus(true);
                Gerenciador.getHeroi().setDesenhar(false);
                Gerenciador.getHeroi().arrumar();
            
                try{
                
                    Thread.sleep(500);
                    
                }
                catch(InterruptedException e){
                
                    System.err.println(e);
                
                }
                
            }
                                   
        }
        else{
        
            gerenciador.keyPressed(ke);
        
        }
        
        if(StatusSalvar.isTelaStatus()){
                            
            StatusSalvar.keyPressed(ke);
            
        }
    
    }

    //quando o teclado é solto
    @Override
    public void keyReleased(KeyEvent ke) {
    
        if(gerenciador.isMoverPersonagem()){
        
            if(ke.getKeyCode() == KeyEvent.VK_W){
            
                Gerenciador.getHeroi().setCima(false);
                Gerenciador.getHeroi().setCimaParado(true);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
            
            }
        
            if(ke.getKeyCode() == KeyEvent.VK_S){
        
                Gerenciador.getHeroi().setBaixo(false);
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(true);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(false);
        
            }
        
            if(ke.getKeyCode() == KeyEvent.VK_A){
        
                Gerenciador.getHeroi().setEsquerda(false);
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(true);
                Gerenciador.getHeroi().setDireitaParado(false);
        
            }
        
            if(ke.getKeyCode() == KeyEvent.VK_D){
        
                Gerenciador.getHeroi().setDireita(false);
                Gerenciador.getHeroi().setCimaParado(false);
                Gerenciador.getHeroi().setBaixoParado(false);
                Gerenciador.getHeroi().setEsquerdaParado(false);
                Gerenciador.getHeroi().setDireitaParado(true);
                    
            }
                           
        }
        else{
        
            gerenciador.keyReleased(ke);
        
        }
    
    }
    
    public void carregar(){
    
        try{
        
            InputStream os = getClass().getResourceAsStream("/Arquivos/oi.obj");            
            ObjectInputStream os2 = new ObjectInputStream(os);
                                  
            s = (Salvar)os2.readObject();
            
            os2.close();
                                    
            Gerenciador.getHeroi().setNome(s.nome);
            Gerenciador.getHeroi().setX(s.x);
            Gerenciador.getHeroi().setY(s.y);
            Gerenciador.getHeroi().setSpe(s.spe);
            Gerenciador.getHeroi().setVivo(s.vivo);
            Gerenciador.getHeroi().setMp(s.mp);
            Gerenciador.getHeroi().setMaxMp(s.maxMp);
            Gerenciador.getHeroi().setMaxHp(s.maxHp);
            Gerenciador.getHeroi().setMapaAtual(s.mapaAtual);
            Gerenciador.getHeroi().setIntl(s.intl);
            Gerenciador.getHeroi().setHp(s.hp);
            Gerenciador.getHeroi().setExpProx(s.expProx);
            Gerenciador.getHeroi().setExp(s.exp);
            Gerenciador.getHeroi().setDinheiro(s.dinheiro);
            Gerenciador.getHeroi().setDef(s.def);
            Gerenciador.getHeroi().setAtk(s.atk);   
            Gerenciador.eventos = s.acoes;
            
            
        }
        catch(IOException | ClassNotFoundException e){
            
            System.err.println(e);
            
        }
    
    }
    
}
