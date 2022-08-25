//@author Isaias Nascimento Caetano Pinto

package Mapa;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mapa{
    
    //Atributos
    private int x;
    private int y;
    private int tamanhoTile;
    private int largura;
    private int altura;
    private int mapa[][];
    
    //Array de objetos tile, e imagem da tile
    private BufferedImage tileSet;
    private Tile tiles[][];
        
    /*
     *Métodos get e set
     */
    public int getX() {
        
        return x;
        
    }

    public void setX(int x) {
        
        this.x = x;
        
    }

    public int getY() {
        
        return y;
        
    }

    public void setY(int y) {
        
        this.y = y;
        
    }

    public int getTamanhoTile() {
        
        return tamanhoTile;
        
    }    

    public void setTamanhoTile(int tamanhoTile) {
        
        this.tamanhoTile = tamanhoTile;
        
    }
    
    /*Construtor:
    *Recebe a altura e largura da tile
    *Recebe o array de int que representa o mapa
    *Define o tamanho da tile
    */
    public Mapa(int largura, int altura, int mapa[][]){
        
           this.largura = largura;
           this.altura = altura;
           this.mapa = mapa;

           tamanhoTile = 32;
                      
    }
    
    //Estes dois métodos retornam a posição de algum objeto no mapa
    public int pegarColunaTile(int x){
    
        return x / tamanhoTile;
    
    }
    
    public int pegarLinhaTile(int y){
    
        return y / tamanhoTile;
    
    }
    
    //Retorna o número, correspondente aos valores do parametro
    public int pegarTileAtual(int x, int y){
    
        return mapa[y][x];
    
    }
    
    //Pega a imagem, divide ele e cria tilesets
    public void pegarTile(String local){
    
        try{
        
            //Pega a imagem
            tileSet = ImageIO.read(getClass().getResource(local));
                        
            //Pega a largura da imagem e divide pelo tamanho de uma tile
            int colunas = tileSet.getWidth() / tamanhoTile;
            
            //Aloca memória para o array de imagens
            tiles = new Tile[2][colunas];
            
            //Guarda a imagem que será dividida e adicionada em algum desses arrays
            BufferedImage imagemDividida;
            
            for(int col = 0; col < colunas; col ++){
            
                /*
                *divide a imagem e adiciona ela em um array
                *Caso a linha do array seja 0, a tile criada não é colidível
                *Caso a linha do array seja 1, a tile criada é colidível 
                */
                imagemDividida = tileSet.getSubimage(col * tamanhoTile, 0, tamanhoTile, tamanhoTile);
                tiles[0][col] = new Tile(imagemDividida, false);
                
                imagemDividida = tileSet.getSubimage(col * tamanhoTile, tamanhoTile, tamanhoTile, tamanhoTile);
                tiles[1][col] = new Tile(imagemDividida, true);
                
            }
            
        }
        catch(IOException e){
            
            System.err.println(e);
        
        }       
    
    }
    
    //pega a tile pedida, e ver se ela é colidivel
    public boolean isColidivel(int linha, int coluna) {

        int lc = mapa[linha][coluna];

        int l = lc / tiles[0].length;
        int c = lc % tiles[0].length; //isso aqui é uma das técnicas de colisão

        return tiles[l][c].isColidivel();

    }
    
    //desenha as tiles
    public void draw(Graphics2D g2) {

        for (int linha = 0; linha < altura; linha++) {

            for (int coluna = 0; coluna < largura; coluna++) {

                int lc = mapa[linha][coluna];

                int l = lc / tiles[0].length;
                int c = lc % tiles[0].length;
                
                g2.drawImage(tiles[l][c].getTile(), x + coluna * tamanhoTile, y + linha * tamanhoTile, null);
                                
            }

        }

    }
    
}
