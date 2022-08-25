package Salvar;

import java.io.Serializable;

public class Salvar implements Serializable{
    
    //Variáveis importantes para salvar o game
    public String mapaAtual;
    public double x;
    public double y;
    public boolean [] acoes;
    
    //Variáveis para atributos do personagem em batalha
    public int hp;
    public int mp;
    public int maxHp;
    public int maxMp;
    public int level;
    public int exp;
    public int expProx;
    public double atk;
    public double def;
    public double spe;    
    public double intl;
    public boolean vivo;
    public int dinheiro;
    public String sexo;
    public String nome;
    
}
