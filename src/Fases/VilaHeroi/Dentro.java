package Fases.VilaHeroi;

import Fases.Gerenciador;
import Mapa.Mapa;
import Musica.Musicas;
import Painel.Painel;
import Personagens.Heroi;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Dentro implements Fases.Fase{
    
    private String [] mensagens = new String[3]; 
    private boolean mensagem;
    private boolean naoClicado;
    private boolean fimDemo;
    private boolean subirNome;
    private int y;
    
    //20x22
    private int map[][] = {{0, 0, 0, 0, 0,0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 0, 0, 0, 0, 0, 0 },
{71, 63, 63, 63, 62, 63, 63, 63, 66, 67, 63, 63, 63, 63, 68, 63, 63, 63, 71, 63 },
{58, 19, 19, 19, 19, 19, 19, 19, 64, 65, 19, 19, 19, 19, 19, 19, 19, 19, 19, 69 },
{59, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 70 },
{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19 },
{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19 },
{58, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 69 },
{59, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 60, 61, 19, 19, 19, 70 },
{72, 73, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19 },
{55, 55, 55, 55, 57, 17, 18, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55 },
{51, 51, 53, 53, 14, 15, 16, 51, 52, 52, 53, 53, 52, 52, 52, 52, 54, 52, 52, 52 },
{1, 1, 1, 1, 2, 3, 4, 5, 5, 5, 6, 7, 8, 1, 1, 1, 1, 1, 1, 1 },
{9, 9, 9, 9 ,9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

    //Objetos serão usados
    private Mapa mapa;
    private Heroi heroi;
    private Gerenciador gerenciador;
    private Musicas musicaFundo;
    private final String localMusica;
    
     //Método que retorna o mapa
    public Mapa getMapa() {

        return mapa;

    }
    
    public Dentro(Heroi heroi, Gerenciador gerenciador){
    
        this.heroi = heroi;
        mapa = new Mapa(20, 22, map);
        mapa.pegarTile("/Arquivos/Labirinto/Dentro.png");
        localMusica = "/Musicas/labirinto.mp3";       
        musicaFundo = new Musicas(localMusica, true);
        this.gerenciador = gerenciador;
        naoClicado = true;
        
        y = Painel.altura - 40;
                        
        Gerenciador.setMoverPersonagem(true);
        
    }
        
    @Override
    public void update() {
    
        if(heroi.getX() > 234 && heroi.getX() < 337 && heroi.getY() < 154 && naoClicado){
                    
            naoClicado = false;
            mensagem = true;   
            ThreadFrase();
        
        }
        
        if(heroi.getX() < 0){
        
            musicaFundo.parar();
            
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("Castelo");
            gerenciador.conferirMapa();
            
            heroi.setX(130);
            heroi.setY(135);
        
        }
        
        if(y <= 10){
        
            subirNome = false;
            System.exit(0);
        
        }
        
    }

    private void ThreadFrase(){
    
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            
                gerenciador.setMoverPersonagem(false);
                heroi.arrumar();
                
                try{
                
                    mensagens[0] = "Olá";
                    mensagens[1] = heroi.getNome() + ": Quem é?";
                    mensagens[2] = "Não é da tua conta";
                            
                    Thread.sleep(5000);
                    
                    mensagens[0] = heroi.getNome() + ": Exijo saber quem é.";
                    mensagens[1] = heroi.getNome() + ": Ou tú morrerás por minha espada.";
                    mensagens[2] = "Muito bem, jovem tolo.";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = "Sou um fantasma que vive vagando.";
                    mensagens[1] = "Este castelo é tudo o que me resta.";
                    mensagens[2] = "O grande mal assolou esta Terra.";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = "E só há uma forma de vencer esta mal.";
                    mensagens[1] = heroi.getNome() + ": Qual seria esta forma,";
                    mensagens[2] = heroi.getNome() + ": caro fantasma";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = "Encontrar os livros do poder.";
                    mensagens[1] = heroi.getNome() + ": Onde encontro-os?";
                    mensagens[2] = "Apenas siga o caminho que sentir";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = "Boa sorte.";
                    mensagens[1] = "Até mais,";
                    mensagens[2] = "Caro herói ...";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = heroi.getNome() + ": Não teu maldito, não vá,";
                    mensagens[1] = heroi.getNome() + ": Preciso de mais informações...";
                    mensagens[2] = "";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = heroi.getNome() + ": Maldito...";
                    mensagens[1] = "";
                    mensagens[2] = "";
                    
                    Thread.sleep(5000);
                    
                    mensagens[0] = heroi.getNome() + ": Não me ajudou em nada...";
                    mensagens[1] = heroi.getNome() + ": Só me deixou com mais dúvida";
                    mensagens[2] = heroi.getNome() + ": Melhor ir...";

                    mensagem = false;
                    gerenciador.setMoverPersonagem(true);
                    
                    Thread.sleep(5000);
                    fimDemo = true;
                    
                    Thread.sleep(3000);
                    subirNome = true;
                    ThreadNomes();
                    
                }
                catch(InterruptedException ex){
                
                    System.err.println(ex);
                
                } 
                
            }
            
        });
        
        t.start();
        
    }
    
    @Override
    public void draw(Graphics2D grafico) {
    
        mapa.draw(grafico);
    
        if(mensagem){
                    
            grafico.setColor(new Color(25,25,80));
            grafico.fillRect(0, 0, Painel.largura, 70 * 2);
            grafico.setColor(Color.WHITE);
            grafico.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,25));
                        
            grafico.drawString(mensagens[0], 20, 30);
            grafico.drawString(mensagens[1], 20, 60);
            grafico.drawString(mensagens[2], 20, 90);
            
        }
        if(fimDemo){
        
            grafico.setColor(Color.BLACK);
            grafico.fillRect(0, 0, Painel.largura, Painel.altura);
            
            grafico.setColor(Color.WHITE);
            grafico.drawString("Fim da versão demo", Painel.largura / 2, Painel.altura / 2 - 50);
                    
        }        
        
        if(subirNome){
        
            grafico.setColor(Color.BLACK);
            grafico.fillRect(0, 0, Painel.largura, Painel.altura);
            
            grafico.setColor(Color.WHITE);
            grafico.drawString("Esse jogo não é bom. Fica a dica.",Painel.largura / 2 - 200, y);
        
        }
        
    }

    private void ThreadNomes(){
    
        fimDemo = false;
        
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
            
                while(subirNome){
                
                    try{
                    
                        y -= 10;
                        Thread.sleep(500);
                    
                    }
                    catch(InterruptedException ex){
                    
                        System.err.println(ex);
                    
                    }
                                    
                }
                
            }
            
        });
        
        t.start();
    
    }
    
    @Override
    public void criarMonstros() {}

    @Override
    public void delayMusical() {}

    
    
}
