package Fases.VilaHeroi;

import Fases.Gerenciador;
import Mapa.Mapa;
import Musica.Musicas;
import Personagens.Heroi;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import Batalha.Batalha;
import Monstros.DesenharMonstro;
import Monstros.Monstro;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VilaHeroi implements Fases.Fase, KeyListener {

    //Mapa da vila
    private int map[][] = {{16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16},
    {16, 25, 25, 25, 25, 25, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16},
    {16, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 27, 0, 0, 0, 0, 0, 0, 0, 0, 29, 29, 29, 29, 29, 16},
    {16, 24, 23, 24, 24, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 26, 27, 0, 0, 0, 0, 0, 0, 0, 0, 28, 28, 28, 28, 28, 16},
    {16, 21, 22, 21, 21, 21, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 2, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 2, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 2, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 0, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 16},
    {16, 20, 1, 0, 0, 0, 0, 0, 0, 0, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 20, 1, 0, 0, 0, 0, 0, 0, 0, 24, 24, 24, 23, 24, 24, 24, 24, 24, 1, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 16},
    {16, 0, 1, 0, 0, 0, 0, 0, 0, 1, 21, 21, 21, 22, 21, 21, 21, 21, 21, 0, 0, 1, 2, 0,0, 26, 27, 0, 0, 26, 27, 16},
    {16, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 26, 27, 2, 0, 26, 27, 16},
    {16, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 16},
    {16, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 20, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 1, 1, 1, 1, 1, 1, 0, 2, 0, 0, 0, 16},
    {16, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 2, 0, 0, 16},
    {16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 26, 27, 16},
    {16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 16},
    {16, 0, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 0, 1, 18, 30, 0, 0, 18, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 16},
    {16, 0, 0, 1, 18, 31, 2, 0, 18, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16},
    {16, 0, 1, 1, 0, 31, 0, 0, 2, 0, 0, 18, 0, 0, 3, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 18, 18, 18, 19, 16},
    {16, 0, 1, 1, 0, 31, 0, 2, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 19, 0, 0, 0, 0, 0, 18, 18, 19, 19, 16},
    {16, 1, 1, 1, 1, 31, 0, 0, 0, 2, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 19, 18, 19, 16},
    {16, 1, 1, 1, 1, 31, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 16},
    {16, 1, 1, 1, 1, 31, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16}};

    //Objetos serão usados
    private Mapa mapa;
    private Heroi heroi;
    private Gerenciador gerenciador;
    private Musicas musicaFundo;
    private Musicas musicaBatalha;
    private Rectangle aviso;
    private Rectangle local;
    private boolean mensagemTrancado;
    private boolean emBatalha;
    private final String localMusica;
    private final String localBatalha;
    private ArrayList<Monstros.Monstro> monstros;
    private Batalha batalha;
    
    //Posição e movimentação do monstro
    private int x = 20;
    private boolean direita = true;
    private boolean desenharMonstro;
    
    
    DesenharMonstro dm = new DesenharMonstro();
    
    //Método que retorna o mapa
    public Mapa getMapa(){
    
        return mapa;
    
    }
        
    //Contrutor
    public VilaHeroi(Heroi heroi, Gerenciador gerenciador){
    
        this.heroi = heroi;
        
        //Cria o mapa
        mapa = new Mapa(32, 30, map);
        mapa.pegarTile("/Arquivos/VilaHeroi/VilaHeroi.png");
        localMusica = "/Musicas/labirinto.mp3";                
        localBatalha = "/Musicas/batalha.mp3";
        this.gerenciador = gerenciador;
        mensagemTrancado = false;
        emBatalha = false;
        aviso = new Rectangle(429, 464, 32,32);
        local = new Rectangle(48,782,62,32);
        desenharMonstro = true;
        
        gerenciador();        
        Gerenciador.setMoverPersonagem(true);
               
    }
    
    @Override
    public void update() {
                        
        //Move para a casa
        if(heroi.getY() <= 184 && (heroi.getX() > 80 && heroi.getX() < 104)){
        
            musicaFundo.parar();
            
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("VilaInicial");
            gerenciador.conferirMapa();
            
            heroi.setX(48);
            heroi.setY(333);
        
        }
        
        //Move para o labirinto
        if(heroi.getY() > 916){
        
            musicaFundo.parar();
            
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("Labirinto");
            gerenciador.conferirMapa();
            
            heroi.setX(490);
            heroi.setY(740);
        
        }
        
        //Mostra a mensagem de porta trancada
        if(heroi.getRetangulo().intersects(aviso)){
        
            mensagemTrancado = true;
        
        }
        else{
        
            mensagemTrancado = false;
        
        }

        //Entra na tela de batalha
        if(heroi.getRetangulo().intersects(local)){
        
            local = new Rectangle(9999,9999,1,1);
            
            criarMonstros();
            emBatalha = true;
            
            gerenciador();
            musicaFundo.parar();
            
            Gerenciador.setMoverPersonagem(false);
            Gerenciador.getHeroi().arrumar();
            
            heroi.setEsquerda(false);
            heroi.setDireita(false);
            heroi.setCima(false);
            heroi.setBaixo(false);
            heroi.setDireitaParado(false);
            heroi.setCimaParado(false);
            heroi.setBaixoParado(false);
            heroi.setEsquerdaParado(true);            
        
        }
        
        //Sai da tela de batalha
        else if(batalha != null && batalha.isTerminou()){
        
            emBatalha = false;
            Gerenciador.setMoverPersonagem(true);
            heroi.setDesenhar(true);
            batalha = null;
            gerenciador();
            
        }
                        
    }
    
    @Override
    public void draw(Graphics2D grafico) {
        
        if(emBatalha){
            
            //Desenha a tela de batalha
            batalha.draw(grafico);
            desenharMonstro = false;
            
        }        
        else{
            
            //Desenha o mapa
            mapa.draw(grafico);
           
            if(desenharMonstro){
            
                if(direita){
            
                    dm.desenhar(grafico, x, 764, 0, mapa);
                    if(x == 110)direita = false;
                    x++;
            
                }
                else{
            
                    dm.desenhar(grafico, x, 764, 1, mapa);
                    if(x == 40)direita = true;
                    x--;
            
                }
                                      
            }
            
            if(mensagemTrancado){
        
                //Draw a rect
                grafico.setColor(new Color(25,25,80));
                grafico.fillRect(0, 0, Painel.Painel.largura, 70);
            
                //Draw the message
                grafico.setColor(Color.WHITE);
                grafico.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,25));
                grafico.drawString("A porta está trancada.", 20, 30);
        
            }
                    
        }   
                
    }

    @Override
    public void criarMonstros() {
        
        monstros = new ArrayList<>();
        monstros.clear();
        
        for(int a = 0; a < 3; a++){
        
            monstros.add(a, new Monstro());
            monstros.get(a).setNome("Monstro " + String.valueOf(( a + 1)));
            monstros.get(a).setSpe(10);
            monstros.get(a).setMaxHp(10);
            monstros.get(a).setExp(10);
            monstros.get(a).setAtk(4);
            monstros.get(a).setDinheiro(10);
                    
        }
                
    }

    @Override
    public void delayMusical() {
    
        try{
        
            Thread.sleep(300);
        
        }
        catch(InterruptedException e){
        
            System.err.println(e);
        
        }
    
    }

    private void gerenciador(){
    
        if(emBatalha){
            
            musicaFundo.parar();
            heroi.setDesenhar(false);
            batalha = new Batalha("/Arquivos/Fundo.jpg", heroi,monstros);
            musicaBatalha = new Musicas("/Musicas/MusicaBatalha.mp3", true);
            
        }
        else{
        
            if(musicaBatalha != null){
            
                musicaBatalha.parar();
                musicaBatalha = null;
            
            }
            
            musicaFundo = new Musicas("/Musicas/labirinto.mp3", true);
        
        }
    
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    
        if(emBatalha){
        
            batalha.keyTyped(ke);
        
        }        
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(emBatalha){
        
            batalha.keyPressed(ke);
        
        } 
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    
        if(emBatalha){
        
            batalha.keyReleased(ke);
        
        }
        
    }
    
}
