package Fases.VilaHeroi;

import Fases.Gerenciador;
import Mapa.Mapa;
import Musica.Musicas;
import Personagens.Heroi;
import java.awt.Graphics2D;

public class Castelo implements Fases.Fase{

    //7x7
    private int map[][] = {{47, 48, 49, 50, 51, 52, 53},
    {40, 41, 42, 43, 44, 45, 46},
    {38, 34, 35, 36, 37, 38, 39},
    {1, 2, 3, 4, 5, 6, 6},
    {1, 2, 3, 4, 5, 6, 6},
    {1, 2, 3, 4, 5, 6, 6},
    {2, 2, 3, 4, 5, 6, 6}};

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

    public Castelo(Heroi heroi, Gerenciador gerenciador){
    
        this.heroi = heroi;
        mapa = new Mapa(7, 7, map);
        mapa.pegarTile("/Arquivos/Labirinto/TileCastelo.png");
        localMusica = "/Musicas/labirinto.mp3";       
        musicaFundo = new Musicas(localMusica, true);
        this.gerenciador = gerenciador;
                        
        Gerenciador.setMoverPersonagem(true);
    
    }

    @Override
    public void update() {
    
        if(heroi.getY() > 195 || heroi.getX() < 0 || heroi.getX() > 200){
        
            musicaFundo.parar();
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("Labirinto");
            gerenciador.conferirMapa();
                
            heroi.setY(33);
            heroi.setX(120);
            
        }
    
        if(heroi.getY() < 130){
        
            musicaFundo.parar();
            Gerenciador.setMoverPersonagem(false);
            heroi.setMapaAtual("Dentro");
            gerenciador.conferirMapa();
                
            heroi.setX(64);
            heroi.setY(400);
        
        }
        
    }

    @Override
    public void draw(Graphics2D grafico) {
    
        mapa.draw(grafico);
    
    }

    @Override
    public void criarMonstros() {}

    @Override
    public void delayMusical() {}
    
}
