/**
 *
 * @author isaiasnascimento
 */

package Fases;

import Fases.VilaHeroi.CasaHeroi;
import Fases.VilaHeroi.Castelo;
import Fases.VilaHeroi.Dentro;
import Fases.VilaHeroi.Labirinto;
import Fases.VilaHeroi.VilaHeroi;
import Mapa.Mapa;
import MenuInicial.TelaNome;
import Personagens.*;
import Salvar.Salvar;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
*Esta classe irá gerencia os mapas e lugares por onde o jogador andar. 
*/
public class Gerenciador implements KeyListener{
    
    //Boleano para os eventos do jogo
    public static boolean eventos[];
    //Objeto heroi;
    private static Heroi heroi;    
    //Mapa do jogo
    private Mapa mapa;    
    //Objeto para poder usar os gráficos
    private Graphics2D graficos;
    //Faz com o que o personagem possa se mover ou não
    private static boolean moverPersonagem = false;    
    
    //Objetos dos mapas
    private CasaHeroi vila;
    private VilaHeroi vilaHeroi;
    private Labirinto labirinto;
    private Castelo castelo;
    private Dentro dentro;
  
    //Objeto para carregar dados
    private Salvar s;
    
    ////////////////////////////////////////////////////////////////////////////
    //get e set
    ////////////////////////////////////////////////////////////////////////////
    public boolean[] getEventos() {        
        return eventos;        
    }
    
    public static Heroi getHeroi() {
        return heroi;
    }
    
    public void setHeroi(Heroi heroi) {
        this.heroi = heroi;
    }
        
    public Graphics2D getGraficos() {
        return graficos;
    }
    
    public void setGraficos(Graphics2D graficos) {
        this.graficos = graficos;
    }

    public static boolean isMoverPersonagem() {
        return moverPersonagem;
    }

    public static void setMoverPersonagem(boolean moverPersonagens) {
        moverPersonagem = moverPersonagens;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    //Construtor usado no inicio do jogo  
    public Gerenciador(){
    
        moverPersonagem = true;
        heroi = new Heroi();
        heroi.setNome(TelaNome.nome);
                        
        vila = new CasaHeroi(heroi, this);
        
        heroi.setMapa(vila.getMapa());
        heroi.pegarImagens();
        heroi.setY(67);
        heroi.setX(48);
        heroi.setMapaAtual("VilaInicial");
        
    }
    
    //Quando carrega um jogo salvo
    public Gerenciador(Salvar s){
    
        this.s = s;
        moverPersonagem = true;        
        heroi = new Heroi();
        heroi.pegarImagens();
        
    }
    
    /*
    Método para conferir o mapa em que o jogador irá
    */
    public void conferirMapa(){
     
        //Se o ultimo lugar onde o personagem estava era vila inicial, ele cria a vila inicial
        if(heroi.getMapaAtual().equals("VilaInicial")){
                    
            vila = new CasaHeroi(heroi,this);               
            heroi.setMapa(vila.getMapa());
                                
        }       
        else if(heroi.getMapaAtual().equals("VilaInicial1")){
        
            vilaHeroi = new VilaHeroi(heroi, this);
            heroi.arrumar();
            heroi.setMapa(vilaHeroi.getMapa());      
            
        }
        else if(heroi.getMapaAtual().equals("Labirinto")){
        
            labirinto = new Labirinto(heroi, this);
            heroi.arrumar();
            heroi.setMapa(labirinto.getMapa());
        
        }
        else if(heroi.getMapaAtual().equals("Castelo")){
        
            castelo = new Castelo(heroi,this);
            heroi.arrumar();
            heroi.setMapa(castelo.getMapa());
        
        }
        else if(heroi.getMapaAtual().equals("Dentro")){
            
            dentro = new Dentro(heroi, this);
            heroi.arrumar();
            heroi.setMapa(dentro.getMapa());
            
        }
        
    }    
        
    //Método para atualizar
    public void update(){
        
        //atualiza o Heroi
        heroi.update();
           
        if(heroi.getMapaAtual().equals("VilaInicial")){
        
            vila.update();
                            
        }
        if(heroi.getMapaAtual().equals("VilaInicial1")){
        
            vilaHeroi.update();
            
        }
        if(heroi.getMapaAtual().equals("Labirinto")){
        
            labirinto.update();
            
        }
        if(heroi.getMapaAtual().equals("Castelo")){
        
            castelo.update();
        
        }
        if(heroi.getMapaAtual().equals("Dentro")){
        
            dentro.update();
        
        } 
        
    }
    
    //Método para desenhar
    public void draw(Graphics2D grafico){
    
        if(heroi.getMapaAtual().equals("VilaInicial")){
        
            vila.draw(grafico);
                            
        }
        if(heroi.getMapaAtual().equals("VilaInicial1")){
        
            vilaHeroi.draw(grafico);
            
        }
        
        if(heroi.getMapaAtual().equals("Labirinto")){
        
            labirinto.draw(grafico);
            
        }
        
        if(heroi.getMapaAtual().equals("Castelo")){
        
            castelo.draw(grafico);
        
        }
        
        if(heroi.getMapaAtual().equals("Dentro")){
        
            dentro.draw(grafico);
        
        }
        
        //Deseha o heroi
        
        heroi.draw(grafico);
        
    }

    //Key listeners
    @Override
    public void keyTyped(KeyEvent ke) {
    
        if(heroi.getMapaAtual().equals("VilaInicial1")){
            
            vilaHeroi.keyTyped(ke);
            
        }
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    
        //Faz com que possa se usar keyPressed do objeto Vila inicial
        if(heroi.getMapaAtual().equals("VilaInicial1")){
            
            vilaHeroi.keyPressed(ke);
            
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    
        if(heroi.getMapaAtual().equals("VilaInicial1")){
            
            vilaHeroi.keyReleased(ke);
            
        }
        
    }
            
}
