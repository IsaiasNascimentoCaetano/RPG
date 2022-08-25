package Fases;

//Essa é uma interface que serve para as fases
import java.awt.Graphics2D;

public interface Fase{
    
    //Update do mapa
    public void update();
    
    public void draw(Graphics2D grafico);
    
    //Se houver monstros
    public void criarMonstros();
    
    //Sempre espera antes de mudar de tela
    public void delayMusical();
    
}
