//@author Isaias Nascimento Caetano Pinto

package Mapa;

import java.awt.image.BufferedImage;

/*
*Esta classe pega as tiles, que são pedaços de uma imagem
*pega as informações da tile, como se ela e colidivel ou não
*/
public class Tile {
    
    private BufferedImage tile;
    private boolean colidivel;
    
    //Métodos get
    public BufferedImage getTile() {
        
        return tile;
        
    }

    public boolean isColidivel() {
        
        return colidivel;
        
    }
    
    /*
    *recebe a imagem
    *recebe o boolean que faz ela ser colidivel ou não
    */
    public Tile(BufferedImage tile, boolean colidivel){
    
        this.tile = tile;
        this.colidivel = colidivel;
    
    }
    
}
