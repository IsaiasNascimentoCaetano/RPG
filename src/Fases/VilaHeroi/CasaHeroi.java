package Fases.VilaHeroi;

//Essa é a classe que gera a primeira cidade

import Fases.Fase;
import Fases.Gerenciador;
import Mapa.Mapa;
import Musica.Musicas;
import Personagens.Heroi;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CasaHeroi implements Fase{

    //Atributos
    
    private int map[][] = {{5,6,5,5,6,5,6,5,5,5,6},
                           {5,3,0,0,0,1,1,0,0,0,5},
                           {6,4,0,1,0,0,0,1,0,0,6},
                           {6,0,0,0,0,0,0,1,1,0,5},
                           {5,0,0,0,0,0,0,0,1,0,6},
                           {6,1,1,0,0,0,0,1,1,0,6},
                           {5,0,1,1,1,1,0,1,0,1,5},
                           {5,7,7,7,7,7,7,7,2,2,6},
                           {6,8,8,8,8,8,8,8,2,2,5},
                           {5,0,0,0,0,0,0,1,0,0,6},
                           {6,1,0,1,1,0,0,0,1,0,5},
                           {5,0,0,6,5,6,6,5,5,6,5}};
        
    private Mapa mapa;
    private Heroi heroi;
    private Gerenciador gerenciador;
        
    private Musicas musicaFundo;
    
    
    public Mapa getMapa() {
        return mapa;
    }
                
    public CasaHeroi(Heroi heroi,Gerenciador gerenciador){
       
        //Recebe o heroi
        this.heroi = heroi;
        
        Gerenciador.setMoverPersonagem(true);
                
        //cria o mapa
        mapa = new Mapa(11, 12, map);
        mapa.pegarTile("/Arquivos/VilaHeroi/fase1.png");
        
        //recebe o objeto gerenciador
        this.gerenciador = gerenciador;
        
        gerenciador();
        
    }
    
    private Rectangle f = new Rectangle(128,64,32,32);
    
    @Override
    public void update() {
    
        if(heroi.getY() >= 335 && (heroi.getX() >= 48 && heroi.getX() <= (48 + 32))){
        
            //Primeiro para a música e espera pelo delay
            musicaFundo.parar();            
            delayMusical();
            
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("VilaInicial1");
            gerenciador.conferirMapa();
                        
            heroi.setX(82);
            heroi.setY(187);
            
        }
        
    }

    public void gerenciador(){
            
        musicaFundo = new Musicas("/Musicas/mapa.mp3", true);
        
    }
    
    @Override
    public void draw(Graphics2D grafico) {
        
            //Desenha o mapa
            this.mapa.draw(grafico);
        
    }

    @Override
    public void criarMonstros() {
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
    
}
