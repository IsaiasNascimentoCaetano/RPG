/**
 *
 * @author isaiasnascimento
 */

package Monstros;

import Personagens.Animacao;
import Personagens.Personagem;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;

public class Monstro extends Personagem{

    private String localImagem = "/Arquivos/Monstro.png";
    private String localImagemMonstros[];
    
    //Métodos set
    public void setLocalImagem(String localImagem) {
        this.localImagem = localImagem;
    }

    public void setLocalImagemMonstros(String[] localImagemMonstros) {
        this.localImagemMonstros = localImagemMonstros;
    }
    
    
    //Deixa o monstro vivo
    public Monstro() {
        
        super();
        
        vivo = true;
        isMonster = true;
        hp = 10;
        
    }
        
    @Override
    public void pegarImagens(){
    
        animacao = new Animacao();
        
        //Array que receberá as imagens
        esquerdaSprite = new BufferedImage[3];
        direitaSprite = new BufferedImage[3];
        cimaSprite = new BufferedImage[3];
        baixoSprite = new BufferedImage[3];
        esquerdaParadoSprite = new BufferedImage[1];
        direitaParadoSprite = new BufferedImage[1];
        cimaParadoSprite = new BufferedImage[1];
        baixoParadoSprite = new BufferedImage[1];
        
        for(int a = 0;a < 3; a++){
        
            BufferedImage esquerda = null,direita = null,cima = null,baixo = null;
            InputStream e = null, d = null, c = null, b = null;
            
            try{
            
                //Pega as imagens
                e = getClass().getResourceAsStream(localImagemMonstros[0]);
                d = getClass().getResourceAsStream(localImagemMonstros[1]);
                c = getClass().getResourceAsStream(localImagemMonstros[2]);
                b = getClass().getResourceAsStream(localImagemMonstros[3]);
                
                //BufferedImage lê as imagens
                esquerda = ImageIO.read(e);
                direita = ImageIO.read(d);
                cima = ImageIO.read(c);
                baixo = ImageIO.read(b);
                
                //Divide as imagens
                esquerdaSprite[a] = esquerda.getSubimage(a * largura, 0, largura, altura);
                direitaSprite[a] = direita.getSubimage(a * largura, 0, largura, altura);
                cimaSprite[a] = cima.getSubimage(a * largura, 0, largura, altura);
                baixoSprite[a] = baixo.getSubimage(a * largura, 0, largura, altura);
                
                if(a == 1){
                
                    esquerdaParadoSprite[0] = esquerda.getSubimage(a * largura, 0, largura, altura);
                    direitaParadoSprite[0] = direita.getSubimage(a * largura, 0, largura, altura);
                    cimaParadoSprite[0] = cima.getSubimage(a * largura, 0, largura, altura);
                    baixoParadoSprite[0] = baixo.getSubimage(a * largura, 0, largura, altura);
                
                }
                
            }
            catch(IOException ex){
            
                System.err.println(ex);
            
            }
        
        }        
    
    }
    
    public BufferedImage getImagem(){
    
        InputStream is = getClass().getResourceAsStream(localImagem);
        BufferedImage im = null;
        
        try{
        
            im = ImageIO.read(is);
    
        }
        catch(IOException e){
            
            System.err.println(e);
            
        }
        
        return im;
        
    }
    
}
