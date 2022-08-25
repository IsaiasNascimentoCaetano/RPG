/*
* @author Isaias Nascimento Caetano Pinto
*/

package Personagens;

import Mapa.Mapa;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import Painel.Painel;
import java.awt.Graphics2D;

public abstract class Personagem {
    
    //Atributos
    
    //Variáveis importantes para salvar o game
    private String mapaAtual;
    private double x;
    private double y;
    
    //Variáveis para atributos do personagem em batalha
    protected String nome;
    protected int hp;
    protected int mp;
    protected int maxHp;
    protected int maxMp;
    protected int level;
    protected int exp;
    protected int expProx;
    protected double atk;
    protected double def;
    protected double spe;    
    protected double intl;
    protected boolean vivo;//se for positivo o personagem está vivo
    protected int dinheiro;
    
    //Variaveis para a colisão e desenho do personagem em mapa
    private double dx;
    private double dy;
    protected final int largura;
    protected final int altura;
    
    //Colisão com tile
    private boolean topEsqueda;
    private boolean topDireita;
    private boolean botDiteita;
    private boolean botEsquerda;
    
    //Posições, como parado ou andando
    private boolean cima;
    private boolean cimaParado;
    private boolean baixo;
    private boolean baixoParado;
    private boolean esquerda;
    private boolean esquerdaParado;
    private boolean direita;
    private boolean direitaParado;
    
    //velocidades para andar e parar
    private final double andando;
    private final double andandoMax;
    
    //Colisão com o mapa, para eventos
    private Rectangle retangulo;
    
    //Objetos usados para a colisão e animação
    private Mapa mapa;
    protected Animacao animacao;
    
    //Imagens usadas na animação
    protected BufferedImage cimaSprite[];
    protected BufferedImage cimaParadoSprite[];
    protected BufferedImage baixoSprite[];
    protected BufferedImage baixoParadoSprite[];
    protected BufferedImage esquerdaSprite[];
    protected BufferedImage esquerdaParadoSprite[];
    protected BufferedImage direitaSprite[];
    protected BufferedImage direitaParadoSprite[];
    
    //Confere se é monstro ou não
    protected boolean isMonster;
    
    //Serve para conferir se vai desenha ou não
    protected boolean desenhar;
    
      ///////////////////////////////////////////////////////////////////////////
     //Get e set                                                              //
    ///////////////////////////////////////////////////////////////////////////
    public String getMapaAtual() {
        return mapaAtual;
    }

    public void setMapaAtual(String mapaAtual) {
        this.mapaAtual = mapaAtual;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        
        //Confere o hp mudado
        if(hp < 0){
        
            this.hp = 0;
        
        }
        else if(hp > maxHp){
        
            this.hp = maxHp;
        
        }
        else{
        
            this.hp = hp;
        
        }
        
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        
        //Confere o hp mudado
        if(mp < 0){
        
            this.mp = 0;
        
        }
        else if(mp > maxMp){
        
            this.mp = maxMp;
        
        }
        else{
        
            this.mp = mp;
            
        }
        
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public double getSpe() {
        return spe;
    }

    public void setSpe(double spe) {
        this.spe = spe;
    }

    public double getIntl() {
        return intl;
    }

    public void setIntl(double intl) {
        this.intl = intl;
    }

    public boolean isCima() {
        return cima;
    }

    public void setCima(boolean cima) {
        this.cima = cima;
    }

    public boolean isCimaParado() {
        return cimaParado;
    }

    public void setCimaParado(boolean cimaParado) {
        this.cimaParado = cimaParado;
    }

    public boolean isBaixo() {
        return baixo;
    }

    public void setBaixo(boolean baixo) {
        this.baixo = baixo;
    }

    public boolean isBaixoParado() {
        return baixoParado;
    }

    public void setBaixoParado(boolean baixoParado) {
        this.baixoParado = baixoParado;
    }

    public boolean isEsquerda() {
        return esquerda;
    }

    public void setEsquerda(boolean esquerda) {
        this.esquerda = esquerda;
    }

    public boolean isEsquerdaParado() {
        return esquerdaParado;
    }

    public void setEsquerdaParado(boolean esquerdaParado) {
        this.esquerdaParado = esquerdaParado;
    }

    public boolean isDireita() {
        return direita;
    }

    public void setDireita(boolean direita) {
        this.direita = direita;
    }

    public boolean isDireitaParado() {
        return direitaParado;
    }

    public void setDireitaParado(boolean direitaParado) {
        this.direitaParado = direitaParado;
    }

    public Rectangle getRetangulo() {
        return retangulo;
    }

    public void setRetangulo(Rectangle retangulo) {
        this.retangulo = retangulo;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isVivo() {
        
        if(hp <= 0){
        
            vivo = false;
        
        }
        
        return vivo;
        
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getExpProx() {
        return expProx;
    }

    public void setExpProx(int expProx) {
        this.expProx = expProx;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDesenhar(boolean desenhar) {
        this.desenhar = desenhar;
    }
    
    public int getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public boolean isIsMonster() {
        return isMonster;
    }

    public void setIsMonster(boolean isMonster) {
        this.isMonster = isMonster;
    }
    
    public BufferedImage getEsquedaParado(){
    
        return esquerdaParadoSprite[0];
    
    }
    
    public BufferedImage getDireitaParado(){
    
        return direitaParadoSprite[0];
    
    }
                          
    ///////////////////////////////////////////////////////////////////////////
    //Construtor                                                             //
    //*Recebe o mapa como parametro                                          //
    //*Inicia as constanstes                                                 //
    //*Define a largura e altura                                             //
    //*Chama o método para pegar as imagens                                  //
    ///////////////////////////////////////////////////////////////////////////
    public Personagem(){
    
        andando = 0.7;
        andandoMax = 3.21;
    
        largura = 32;
        altura = 48;        
        
        vivo = true;
        
        retangulo = new Rectangle();
        desenhar = true;       
        
    }
    
    //Cria o objeto animação e pega o array de imagens
    public void pegarImagens(){
    
        animacao = new Animacao();
        
        //Array que receberá as imagens
        esquerdaSprite = new BufferedImage[4];
        direitaSprite = new BufferedImage[4];
        cimaSprite = new BufferedImage[4];
        baixoSprite = new BufferedImage[4];
        esquerdaParadoSprite = new BufferedImage[1];
        direitaParadoSprite = new BufferedImage[1];
        cimaParadoSprite = new BufferedImage[1];
        baixoParadoSprite = new BufferedImage[1];
        
        for(int a = 0;a < 4; a++){
        
            BufferedImage esquerda = null,direita = null,cima = null,baixo = null;
            InputStream e = null, d = null, c = null, b = null;
            
            try{
            
                //Pega as imagens
                e = getClass().getResourceAsStream("/Arquivos/allanEsquerda.png");
                d = getClass().getResourceAsStream("/Arquivos/allanDireita.png");
                c = getClass().getResourceAsStream("/Arquivos/allanFrente.png");
                b = getClass().getResourceAsStream("/Arquivos/allanCosta.png");
                
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
                
                if(a == 2){
                
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
    
    /*
    *Esse é o método que cuida da movimentação, animação e colisãodo
    *do personagem.
    */
    public void update(){
    
        if(esquerda){
            
            dx -= andando;
            
            if(dx < -andandoMax){
            
                dx = -andandoMax;
            
            }
        
        }
        
        else if(direita){
        
            dx += andando;
            
            if(dx > andandoMax){
            
                dx = andandoMax;
            
            }
        
        }
        
        else if(cima){
        
            dy -= andando;
            
            if(dy < -andandoMax){
                
                dy = -andandoMax;
                
            }
        
        }
        
        else if(baixo){
        
            dy += andando;
            
            if(dy > andandoMax){
            
                dy = andandoMax;
            
            }
        
        }
        
        //Quando não andar em nenhuma das 4 direção recebe 0 para ficar parado
        else{
        
            dx = 0;
            dy = 0;
        
        }
        
            
        ///////////////////////////////////////////////////////////////////////
        //Colisão, isso me custou as férias estudando e vendo vídeos         //
        ///////////////////////////////////////////////////////////////////////
        
        int colunaAtual = mapa.pegarColunaTile((int) x);
        int linhaAtual = mapa.pegarLinhaTile((int) y);
        
        //Variáveis auxiliares
        double tox = x + dx;
        double toy = y + dy;
        double tempX = x;
        double tempY = y;
        
        //Confere os cantos específicos da tile, no caso é para ver se ele sobe ou desce 
        calcularCantos(x, toy);
        
        if(dy < 0){
            
            //Confere se colidiu em baixo
            if(topEsqueda || topDireita){
            
                //Se colidiu ele fica no limite
                dy = 0;
                tempY = linhaAtual * mapa.getTamanhoTile() + altura / 2;
            
            }
            //Se não colidiu ele adiciona
            else{
            
                tempY += dy;
            
            }
            
        }
        
        if(dy > 0){
        
            if(botEsquerda || botDiteita){
            
                dy = 0;
                tempY = (linhaAtual + 1) * mapa.getTamanhoTile()- altura / 2;
            
            }
            else{
            
                tempY += dy;
            
            }
        
        }
        
        calcularCantos(tox, y);
                
        if(dx < 0){
        
            if(topEsqueda || botEsquerda){
            
                dx = 0;
                tempX = colunaAtual * mapa.getTamanhoTile() + largura / 2;
            
            }
            else{
            
                tempX += dx;
            
            }
        
        }
        
        if(dx > 0){
        
            if(topDireita || botDiteita){
            
                dx = 0;
                tempX = (colunaAtual + 0.9) * mapa.getTamanhoTile() - largura / 2;
                            
            }          
            else{
            
                tempX += dx;
            
            }
        
        }
                
        //Adiciona
        x = tempX;
        y = tempY;
       
        //Faz com que apenas o personagem mova o mapa
        if(!isMonster){
        
            //Faz com que o mapa se mova
            mapa.setX((int) (Painel.largura / 2 - x));
            mapa.setY((int) (Painel.altura / 2 - y));
                
        }
        ////////////////////////////////////////////////////////////////////////
        //animação                                                            //
        ////////////////////////////////////////////////////////////////////////
        if(esquerda){
        
            animacao.setImage(esquerdaSprite);
            animacao.setDelay(100);
        
        }
        else if(direita){
        
            animacao.setImage(direitaSprite);
            animacao.setDelay(100);
        
        }
        else if(cima){
        
            animacao.setImage(cimaSprite);
            animacao.setDelay(100);
        
        }
        else if(baixo){
        
            animacao.setImage(baixoSprite);
            animacao.setDelay(100);
        
        }
        else if(esquerdaParado){
            
           animacao.setImage(esquerdaParadoSprite);
           //Diminui o delay pois a imagem está parada
           animacao.setDelay(-1);
            
        }
        else if(direitaParado){
        
            animacao.setImage(direitaParadoSprite);
            animacao.setDelay(-1);
        
        }
        else if(cimaParado){
        
            animacao.setImage(cimaParadoSprite);
            animacao.setDelay(-1);
        
        }
        else if(baixoParado){
        
            animacao.setImage(baixoParadoSprite);
            animacao.setDelay(-1);
            
        }
            
        //Atualiza a animação
        animacao.update();
        
    }
    
    private void calcularCantos(double x, double y){
    
        //pega os limites das tiles
        int posicaoTileEsqueda = mapa.pegarColunaTile((int)(x - largura / 2));
        int posicaoTileDireita = mapa.pegarColunaTile((int)(x + largura / 2) +1);
        int alturaTile = mapa.pegarLinhaTile((int)(y - altura / 2));
        int baixoTile = mapa.pegarLinhaTile((int)(y + altura / 2) - 1);
        
        //Confere se o personagem colidiu com alguma tile
        topEsqueda = mapa.isColidivel(alturaTile, posicaoTileEsqueda);
        topDireita = mapa.isColidivel(alturaTile, posicaoTileDireita);
        botEsquerda = mapa.isColidivel(baixoTile, posicaoTileEsqueda);
        botDiteita = mapa.isColidivel(baixoTile, posicaoTileDireita);
    
    }
    
    public void draw(Graphics2D g2){
                    
        //Se estiver vivo, desenha no mapa
        if(desenhar){
    
            //Dá valor ao retangulo para a colisão
            retangulo = new Rectangle((int) x,(int) y, largura, altura);
        
            //desenha a imagem do personagem na tela        
            g2.drawImage(animacao.getImage(),(int)(mapa.getX() + x - largura / 2), (int)(mapa.getY() + y - altura / 2), null);
                        
        }
        
    }
    
    public void arrumar(){
    
        baixo = false;
        baixoParado = true;
        cima = false;
        cimaParado = false;
        esquerda = false;
        esquerdaParado = false;
        direita = false;
        direitaParado = false;
    
    } 
    
}
