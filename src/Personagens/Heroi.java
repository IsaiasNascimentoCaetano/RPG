/**
 * @author isaiasnascimento
 */

package Personagens;

import Monstros.Monstro;

public class Heroi extends Personagem {

    ////////////////////////////////////////////////////////////////////////////
    
    /*
     *Chama o método da classe mãe
     *Inicia os atributos do personagem
     */
    public Heroi() {
        
        super();
        
        setBaixoParado(true);
        hp = 26;
        mp = 5;
        maxHp = hp;
        maxMp = mp;
        level = 1;
        atk = 5;
        def = 5;
        spe = 5;
        intl = 1;
        exp = 0;
        expProx = 20;
        dinheiro = 100;
        vivo = true;
        isMonster = false;
                                
    }
    
    /*
    *Esse método é usado para evoluir o personagem
    *A cada evolução o expProx é multiplicado por 2
    */
    public boolean evoluir(){
                
        if(exp >= expProx){
        
            maxHp += 11;
            maxMp += 5;
            hp = maxHp;
            mp = maxMp;            
            level ++;
            atk += 5;
            def += 5;
            spe += 3;
            intl += 2.8;
            expProx *= 2;
        
            return true;
            
        }
        else{
        
            return false;
        
        }
                
    }

    /*
    Simples método de ataque
    */    
    public String atacar(Monstro m){
    
        int quebra = (int) (m.getDef() - atk);
                     
        if(quebra > 0){
        
            quebra = 0;
            
        }
        
        int dano = (int) (m.getHp() + quebra);
        m.setHp(dano);
        
        return "Tirou " + quebra * (-1) + " de " + m.getNome();
        
    }
    public String curar(){
    
        if(mp > 0){
        
            setMp(getMp() - (2 + level));            
            this.setHp((int) (this.getHp() + intl * 2));
            
            return "Curou " + (int)(intl * 2) + " de vida";
        
        }
        
        else{
        
            return "Sem mana para usar curar";
        
        }
    
    }

    public int gety() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}