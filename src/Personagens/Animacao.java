 // @author isaiasnascimento

package Personagens;

import java.awt.image.BufferedImage;

public class Animacao {
    
    //Atributos
    private BufferedImage []imagens;
    private int imagemAtual;    
    private long tempoInicial;
    private long delay;
    
    /*
    *Nesse método, as imagens com as animação do personagem
    *são recebidas.
    *Se a imagem atual for maior ou igual ao array de imagens
    *imagem atual recebe 0, para não ouver ArrayboundsException
    */
    public void setImage(BufferedImage [] imagens){
             
        this.imagens = imagens;
     
        if(imagemAtual >= this.imagens.length){
        
            imagemAtual = 0;
        
        }
               
        
    }
    
    //Altera o delay
    public void setDelay(int delay){
    
        this.delay = delay;
    
    }      
    
    //Pega a imagem atual
    public BufferedImage getImage(){
        
        return imagens[imagemAtual];
                
    }
    
    /*
    *Este método atualiza a imagem atual
    *Com isso acontece o efeito de animação do personagem
    */
    public void update(){
    
        long tempoPassado = (System.nanoTime() - tempoInicial) / 1000000;
        
        if(tempoPassado > delay){
         
            imagemAtual++;
            tempoInicial = System.nanoTime();
            
        }
               
        if(imagemAtual >= imagens.length){
            
            imagemAtual = 0;
        
        }
        
    }
    
}
