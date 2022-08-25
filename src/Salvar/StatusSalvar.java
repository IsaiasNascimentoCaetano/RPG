package Salvar;

import Fases.Gerenciador;
import Painel.Painel;
import java.awt.Graphics2D;
import Personagens.Heroi;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StatusSalvar{
    
    private static boolean status = true;
    private static boolean salvar;
    private static boolean curar;
    private static boolean telaStatus;
    private static String curou = "";
    private static String salvou = "";
    private static Heroi heroi;
    
    //Get e set
    public static boolean isTelaStatus() {
        
        return telaStatus;
        
    }

    public static void setTelaStatus(boolean telaStatus){
        
        StatusSalvar.telaStatus = telaStatus;
        
    }

    public static void setHeroi(Heroi heroi) {
        
        StatusSalvar.heroi = heroi;
        
    }
        
    //Atualiza
    public static void update(){}

    //Desenha
    public static void draw(Graphics2D grafico){
    
        grafico.clearRect(0, 0, Painel.largura, Painel.altura);
        
        grafico.setColor(new Color(25,25,80));
        grafico.fill3DRect(0, 0, Painel.largura, Painel.altura, true);
       
        grafico.setColor(Color.WHITE);
        //Largura das linhas
        grafico.setStroke(new BasicStroke(3));
        grafico.drawRect(0, 0, Painel.largura / 3, Painel.altura);
        
        grafico.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25));
        
        if(status){
        
            grafico.setColor(Color.YELLOW);
            grafico.drawString("> Status <", 20, 60);
        
        }
        else{
        
            grafico.setColor(Color.WHITE);
            grafico.drawString("  Status  ", 20, 60);
        
        }
        if(salvar){
        
            grafico.setColor(Color.YELLOW);
            grafico.drawString("> Salvar <", 20, 100);
            
        }
        else{
        
            grafico.setColor(Color.WHITE);
            grafico.drawString("  Salvar  ", 20, 100);
        
        }
        if(curar){

            grafico.setColor(Color.YELLOW);
            grafico.drawString("> Curar  <", 20, 140);
            
        }
        else{
        
            grafico.setColor(Color.WHITE);
            grafico.drawString("  Curar  ", 20, 140);
        
        }
        
         desenharAcoes(grafico);
        
    }

    private static void desenharAcoes(Graphics2D grafico){
    
        grafico.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 25));
        
        if(status){
        
            grafico.drawString(Gerenciador.getHeroi().getNome(), Painel.largura / 3 + 20, 55);
            
            grafico.drawString("Hp: " + String.valueOf(Gerenciador.getHeroi().getHp()), 
                    Painel.largura / 3  + 20, 100);
            
            grafico.drawString("Mp: " + String.valueOf(Gerenciador.getHeroi().getMp()), 
                    Painel.largura / 3  + 200, 100);
            
            grafico.drawString("Lv: " + String.valueOf(Gerenciador.getHeroi().getLevel()), 
                    Painel.largura / 3  + 20, 180);
            
            grafico.drawString("Atk: " + String.valueOf((int)Gerenciador.getHeroi().getAtk()),
                    Painel.largura / 3  + 200, 140);
            
            grafico.drawString("Def: " + String.valueOf((int)Gerenciador.getHeroi().getDef()),
                    Painel.largura / 3 + 20, 140);
            
            grafico.drawString("Int: " + String.valueOf((int)Gerenciador.getHeroi().getIntl()),
                    Painel.largura / 3 + 200, 180);
            
            grafico.drawString("Exp: " + String.valueOf(Gerenciador.getHeroi().getExp()),
                    Painel.largura / 3 + 20,  220);
            
            grafico.drawString("Prox Lv: " + String.valueOf(Gerenciador.getHeroi().getExpProx()),
                    Painel.largura / 3 + 20, 260);
            
            grafico.drawString("Dinheiro: " + String.valueOf(Gerenciador.getHeroi().getDinheiro()),
                    Painel.largura / 3 + 20, 300);
            
        }
        if(curar){
        
            grafico.drawString(curou, Painel.largura / 3 + 20, 60);
        
        }
        if(salvar){
        
            grafico.drawString(salvou, Painel.largura / 3 + 20, 60);
        
        }
    
    }
    
    private static String salvar(){
    
        try{
        
            Salvar s = new Salvar();
            s.acoes = Gerenciador.eventos;
            s.x = Gerenciador.getHeroi().getX();
            s.y = Gerenciador.getHeroi().getY();
            s.atk = Gerenciador.getHeroi().getAtk();
            s.def = Gerenciador.getHeroi().getDef();
            s.dinheiro = Gerenciador.getHeroi().getDinheiro();
            s.exp = Gerenciador.getHeroi().getExp();
            s.expProx = Gerenciador.getHeroi().getExpProx();
            s.hp = Gerenciador.getHeroi().getHp();
            s.intl = Gerenciador.getHeroi().getIntl();
            s.level = Gerenciador.getHeroi().getLevel();
            s.mapaAtual = Gerenciador.getHeroi().getMapaAtual();
            s.maxHp = Gerenciador.getHeroi().getMaxHp();
            s.maxMp = Gerenciador.getHeroi().getMaxMp();
            s.mp = Gerenciador.getHeroi().getMp();
            s.spe = Gerenciador.getHeroi().getSpe();
            s.vivo = Gerenciador.getHeroi().isVivo();
            s.nome = Gerenciador.getHeroi().getNome();
        
            OutputStream os = new FileOutputStream("oi.obj");
            
            ObjectOutputStream os2 = new ObjectOutputStream(os);
            os2.writeObject(s);
            
            os2.flush();
        
            System.out.println("salvo");
            
            return "Salvo";
                
        }
        catch(IOException e){
            
            System.out.println(e);
            return "Erro ao salvar";
                
        }
        
    }
    
    public static void keyPressed(KeyEvent ke) {
    
        if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
        
           telaStatus = false;
           Gerenciador.getHeroi().setDesenhar(true);
           Gerenciador.setMoverPersonagem(true);
            
        }
    
        if(ke.getKeyCode() == KeyEvent.VK_W){
        
            if(status){
            
                status = false;
                curar = true;
            
            }
            else if(curar){
            
                curar = false;
                salvar = true;
                curou = "";
            
            }
            else if(salvar){
            
                salvar = false;
                status = true;
                salvou = "";           
                
            }
        
        }
        if(ke.getKeyCode() == KeyEvent.VK_S){
        
            if(status){
            
                salvar = true;
                status = false;
            
            }
            else if(salvar){
            
                salvar = false;
                curar = true;
                salvou = "";
                
            }
            else if(curar){
            
                curar = false;
                status = true;
                curou = "";
                
            }
            
        }
        
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
        
            if(curar){
            
               curar();
            
            }
            if(salvar){
            
                salvou = salvar();
            
            }
        
        }
        
    }

    private static void curar(){
    
        curou = Gerenciador.getHeroi().curar();
    
    }
    
}
