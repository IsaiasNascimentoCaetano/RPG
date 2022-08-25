package Batalha;

import Monstros.Monstro;
import Personagens.Heroi;

/*
Esta classe irá instanciar objetos que servem para guardar o que o jogador irá
fazer a cada turno
*/
public class Acoes {
    
    //Recebe o monstro que o jogador irá atacar
    private Monstro monstro;
    //Recebe o objeto de quem irá recuperar
    private Heroi heroi;
    //Boleanos para identificar o que o jogador irá fazer
    private boolean usarCura;

    public boolean isUsarCura() {
        return usarCura;
    }
    private boolean atacarComArma;
    
    /*
    Construtor para ataque de armas
    */    
    public Acoes(Monstro monstro, Heroi heroi){
    
        this.monstro = monstro;
        this.heroi = heroi;
        
        atacarComArma = true;
        
    }
            
    /*
    Construtor para cura
    */
    public Acoes(Heroi heroi){
    
        this.heroi = heroi;
        usarCura = true; 
    
    }    
    
    /*
    Método que irá fazer a ação que o jogador escolheu
    Retorna uma String com uma mensagem do que foi feito
    */
    public String fazer(){
    
        String mensagem = null;
        
        if(atacarComArma){
        
            mensagem = heroi.atacar(monstro);
        
        }        
        else if(usarCura){
        
            mensagem = heroi.curar();
        
        }       
    
        return mensagem;
        
    }    
    
}
