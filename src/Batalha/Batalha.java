package Batalha;

import Monstros.Monstro;
import Musica.Musicas;
import Painel.Painel;
import Personagens.Heroi;
import Personagens.Personagem;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Batalha implements KeyListener {

    //Atributos
    private BufferedImage fundo;//imagem de fundo
    private Heroi heroi;//Objeto heroi
    private ArrayList<Monstro> monstro;//monstros
    private boolean terminou;//Confere se a batalha terminou
    private int quantidadeHerois;//Vê a quantidade de heróis
    private ArrayList<Acoes> acoes;//Array para guardar as ações do personagem
    private int vezesEnter;//numero de vezes que o personagem apertou enter
    private int vezpersonagem;//Define a vez do persongem, quando hoverem 2
    private boolean iniciarAtaques;//Serve para que a tela de efeitos de batalha seja desenhada
    private ArrayList<Personagem> lista = new ArrayList<Personagem>();//Guarda os personagens para identificar quem é o primeiro
    private int vezAcoes;//Define a vez das ações de batalha
    private String mensagensAposFazer;//Strig que guarda a mensagem que será amostrada durante os efeitos de batalha
    private boolean batalhaTerminada;//Confere se todos os monstros estão mortos e se a batalha foi terminada
    private boolean batalhaTerminada2;//Confere se todos os monstros estão mortos e se a batalha foi terminada
    private int [] ganhos; //Define os ganhos que o jogador teve após batalha, como dinheiro e experiencia
    private boolean evoluiu;//Para conferir se o personagem evoluiu
    private boolean efeitoM;//Desenha as linhas para o efeito do ataque dos monstros
    private boolean [] efeitosB = new boolean[3];//Desenha linhas para efeito de espada de acordo com o monstro
    private boolean fugiu;//Boolean para conferir se o jogador fugiu da tabalha
    private boolean Naofugiu;//Boolean para conferir se o jogador fugiu da tabalha
    private boolean acaoFugir;//Serve para iniciar a tela de fuga
    private boolean morto;
    
    //Retorna um boolean que confere se a batalha terminou sim ou não
    public boolean isTerminou(){
    
        return terminou;
    
    }
    
    ////////////////////////////////////////////////////////////////////////////
    
    //Pega o local da imagem de fundo, pega o objeto heroi e um array de monstros    
    public Batalha(String local, Heroi heroi, ArrayList<Monstro> monstro) {
                
        try {

            //pega a imagem de fundo.
            InputStream is = getClass().getResourceAsStream(local);
            fundo = ImageIO.read(is);

        } 
        catch (IOException e) {

            System.err.println(e);

        }

        terminou = false;
        this.heroi = heroi;
        this.monstro = monstro;
        quantidadeHerois = 1;
        vezpersonagem = 1;
        vezesEnter = 0;
        batalhaTerminada = false;
        acoes = new ArrayList<Acoes>();
        mensagensAposFazer = "";
        
        //Adiciona o personagem e os monstros ao array list
        lista.add(heroi);
        
        for(int a = 0; a < monstro.size(); a++){
        
            lista.add(monstro.get(a));
        
        }
        
        //Inicia um método que cria as falas
        iniciarLetras();
        
    }

    ////////////////////////////////////////////////////////////////////////////
    //Desenhos                                                                //
    ////////////////////////////////////////////////////////////////////////////
    //Desenha as coisas na tela
    public void draw(Graphics2D grafico) {

        //Fica limpando a tela, com isso pode ocorrer a mudança de letras
        grafico.clearRect(0, 0, Painel.largura, Painel.altura);

        //Desenha a imagem de fundo
        grafico.drawImage(fundo, 0, 0, Painel.largura,Painel.altura,null);
        
        //Desenha as informações
        draw1(grafico);
           
    }

    //Variaveis usadas para menu de batalha,m sempre se inicia com a tela de escolha
    private boolean escolha[] = new boolean[4];
    private String Escolhas[] = new String[3];
    private boolean lutar[] = new boolean[4];
    private String nomeMonstros[];
    
    //quando for apenas 1 heroi
    private void draw1(Graphics2D grafico) {
                        
        //Desenha a barra azul
        grafico.setColor(new Color(25, 25, 112));
        grafico.fillRect(0, Painel.altura - 130, Painel.largura, Painel.altura);

        //Desenha a barra branca
        grafico.setColor(Color.WHITE);
        grafico.drawRect(0, Painel.altura - 130, Painel.largura, Painel.altura);
        
        //Se for a tela de escolha
        if (escolha[0]) {
            
            //Desenha as informaçãos do jogador
            grafico.setFont(new Font("", Font.BOLD, 25));
            grafico.drawString(heroi.getNome(), 1, Painel.altura - 100);
            grafico.drawString("HP:" + heroi.getHp(), Painel.largura / 3, Painel.altura - 101);
            grafico.drawString("MP:" + heroi.getMp(), (Painel.largura / 3) * 2, Painel.altura - 101);
            
            //Escreve as opções
            for (int a = 0; a < Escolhas.length; a++) {

                grafico.setFont(new Font("", Font.BOLD, 20));
                
                if (escolha[a + 1]) {

                    grafico.setColor(Color.YELLOW);
                    grafico.drawString(">" + Escolhas[a], 1, Painel.altura - 50 + 20 * a);

                }
                if (!escolha[a + 1]) {

                    grafico.setColor(Color.white);
                    grafico.drawString(" "+Escolhas[a], 1, Painel.altura - 50 + 20 * a);

                }

            }

        }
        
        //Tela para atacar monstros
        else if(lutar[0]){
        
            for(int a = 0; a < monstro.size(); a++){
                
                grafico.setColor(Color.white);
                grafico.setFont(new Font("", Font.BOLD, 25));
                grafico.drawString("Quem " + heroi.getNome() + " ira atacar?" , 1, Painel.altura - 100);
                
                grafico.setFont(new Font("", Font.BOLD, 21));
                
                //Desenha o monstro selecionado
                if(lutar[a + 1] && monstro.get(a).isVivo()){
                
                    grafico.setColor(Color.YELLOW);
                    grafico.drawString(">" + nomeMonstros[a], 1, Painel.altura - 50 + 23 * a);
                    grafico.drawString("HP: " + monstro.get(a).getHp(), Painel.largura / 2,Painel.altura - 50 + 23 * a);
                
                }
                if(!lutar[a + 1] && monstro.get(a).isVivo()){
                
                    grafico.setColor(Color.white);
                    grafico.drawString(" " + nomeMonstros[a], 1, Painel.altura - 50 + 23 * a);
                    grafico.drawString("HP: " + monstro.get(a).getHp(), Painel.largura / 2, Painel.altura - 50 + 23 * a);
                    
                }
                
            }
        
        }
        
        //Mostra os efeitos de batalha
        else if(iniciarAtaques){

            if(vezAcoes == lista.size()){
            
                acoes.clear();//Limpa a lista de ações
                vezAcoes = 0;
                vezesEnter = 0;
                iniciarAtaques = false;
                
                if(!batalhaTerminada){
                
                    escolha[0] = true;
                    escolha[1] = true;
                              
                }
            }
            else{                                 
                
                grafico.drawString(mensagensAposFazer, 1, Painel.altura - 100);                 
                                                
            }
                
        }        
        
        else if(batalhaTerminada){
        
            grafico.setFont(new Font("", Font.BOLD, 20));
            grafico.drawString("Batalha terminada", 1, Painel.altura - 100);        
            grafico.drawString("Ganhou " + ganhos[0] + " de dinheiro", 1, Painel.altura - 100 + 30);
            grafico.drawString("Ganhou " + ganhos[1] + " de experiência", 1, Painel.altura - 100 + 60);
            
            if(evoluiu){
            
                grafico.drawString(heroi.getNome() + " evoluiu de nível", 1, Painel.altura - 100 + 90);
            
            }
            
        }
        
        else if(acaoFugir){
        
            grafico.setFont(new Font("", Font.BOLD, 20));
            
            if(fugiu){
            
                grafico.drawString("Conseguiu fugir", 1, Painel.altura - 100);
                batalhaTerminada2 = true;
            
            }
            else{
                            
                grafico.drawString("Não conseguiu fugir", 1, Painel.altura - 100);
            
                Naofugiu = true;
                               
            }
        
        }
        //Se o personagem for morto, desenha a mensagem de personagem morto.
        else if(morto){
        
            grafico.drawString(mensagensAposFazer, 1,Painel.altura - 100);
                    
        }
        
        //Desenha o persongem e os monstros
        grafico.drawImage(heroi.getEsquedaParado(), Painel.largura - 70, Painel.altura / 3 + 20,32,48,null);
                
        for(int a = 0; a < monstro.size(); a++){
        
            //Se o monstro for vivo, ele é desenhado
            if(monstro.get(a).isVivo()){
                    
                grafico.drawImage(monstro.get(0).getImagem(), 30,80 + 100 * a, null);
                    
            }
                
        }
        
        //Desenha os efeitos de batalha
        for(int a = 0;a < monstro.size(); a++){
        
            //Se o persogem atacou o monstro, e se for a vez do personagem
            if(lista.size() > vezAcoes && lista.get(vezAcoes).getNome().equals(heroi.getNome()) && efeitosB[a]){
            
                grafico.setColor(Color.orange);
                //Aumente o tamanho da linha
                grafico.setStroke(new BasicStroke(8));
                grafico.drawLine(28, 78 + 100 * a, 80, (100 * a) + 200);
                
                efeitosB[a] = false;
            
                //Volta ao tamanho da linha normal                
                grafico.setStroke(new BasicStroke(1));
                
            }
        
        }    
        
        //Se for a vez do monstro atacar, faz o efeito de batalha
        for(int a = 0; a < lista.size(); a++){
            
            if(efeitoM && !lista.get(vezAcoes).getNome().equals(heroi.getNome())){
            
                grafico.setColor(Color.red);
                grafico.setStroke(new BasicStroke(7));
                grafico.drawLine(Painel.largura - 65, Painel.altura / 3 + 85, Painel.largura + 20, Painel.altura / 3 - 50);
                
                efeitoM = false;
                
                grafico.setStroke(new BasicStroke(1));
                
            }
            
        }
        
    }

    ////////////////////////////////////////////////////////////////////////////
    //Eventos                                                                //
    //////////////////////////////////////////////////////////////////////////
    @Override
    public void keyTyped(KeyEvent ke) {
                
    }

    @Override
    public void keyPressed(KeyEvent ke) {
                        
    }

    @Override
    public void keyReleased(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_W) {

            ////////////////////////////////////////////////////////////////////
            if (escolha[0] == true) {

                if (escolha[1]) {

                    escolha[1] = false;
                    escolha[3] = true;
                                         
                }
                else if (escolha[2]) {

                    escolha[2] = false;
                    escolha[1] = true;

                }
                else if (escolha[3]) {

                    escolha[3] = false;
                    escolha[2] = true;

                }

            }
            //Tela de luta
            ////////////////////////////////////////////////////////////////////
            else if(lutar[0]){
            
                //Se for só um monstro
                if(monstro.size() == 1){
                
                    lutar[0] = true;
                
                }
                
                //Se forem 2 monstros
                else if(monstro.size() == 2){
                
                    if(lutar[1]){
                        
                        //Confere se o outro monstro está vivo
                        if(monstro.get(1).isVivo()){
                                                    
                            lutar[1] = false;
                            lutar[2] = true;
                        
                        }
                        
                    }
                    else if(lutar[2]){
                                            
                        if(monstro.get(0).isVivo()){
                        
                            lutar[2] = false;
                            lutar[1] = true;
                        
                        }
                    
                    }
                
                }
                //se forem 3 monstros
                else if(monstro.size() == 3){
                
                    if(lutar[1]){
                        
                        if(monstro.get(2).isVivo()){
                                                    
                            lutar[1] = false;
                            lutar[3] = true;
                        
                        }
                        else if(monstro.get(1).isVivo()){
                        
                            lutar[1] = false;
                            lutar[3] = false;
                            lutar[2] = true;
                        
                        }
                        
                    }
                    else if(lutar[2]){
                                            
                        if(monstro.get(0).isVivo()){
                        
                            lutar[2] = false;
                            lutar[1] = true;
                        
                        }
                        else if(monstro.get(2).isVivo()){
                        
                            lutar[3] = true;
                            lutar[2] = false;
                            lutar[1] = false;
                        
                        }
                    
                    }
                    else if(lutar[3]){
                    
                        if(monstro.get(1).isVivo()){
                        
                            lutar[3] = false;
                            lutar[2] = true;
                        
                        }
                        else if(monstro.get(0).isVivo()){
                        
                            lutar[1] = true;
                            lutar[2] = false;
                            lutar[3] = false;
                        
                        }
                    
                    }
                
                }
                            
            }

        }
        
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        if (ke.getKeyCode() == KeyEvent.VK_S) {

            ///////////////////////////////////////////////////////////////////
            //Tela de escola
            if (escolha[0]) {

                if (escolha[1]) {

                    escolha[1] = false;
                    escolha[2] = true;

                }
                else if (escolha[2]) {

                    escolha[2] = false;
                    escolha[3] = true;

                }
                else if (escolha[3]) {

                    escolha[3] = false;
                    escolha[1] = true;

                }
            }
            
            ////////////////////////////////////////////////////////////////////
            //Se for na tela de luta
            else if(lutar[0]){
            
                //Se for só um monstro
                if(monstro.size() == 1){
                
                    lutar[0] = true;
                
                }
                
                //Se forem 2 monstros
                else if(monstro.size() == 2){
                
                    if(lutar[1]){
                        
                        if(monstro.get(1).isVivo()){
                                                    
                            lutar[1] = false;
                            lutar[2] = true;
                        
                        }
                        
                    }
                    else if(lutar[2]){
                                            
                        if(monstro.get(0).isVivo()){
                        
                            lutar[2] = false;
                            lutar[1] = true;
                        
                        }
                    
                    }
                
                }
                //se forem 3 monstros
                else if(monstro.size() == 3){
                
                    if(lutar[1]){
                        
                        if(monstro.get(1).isVivo()){
                                                    
                            lutar[1] = false;
                            lutar[2] = true;
                        
                        }
                        else if(monstro.get(2).isVivo()){
                        
                            lutar[1] = false;
                            lutar[2] = false;
                            lutar[3] = true;
                        
                        }
                        
                    }
                    else if(lutar[2]){
                                            
                        if(monstro.get(2).isVivo()){
                        
                            lutar[2] = false;
                            lutar[3] = true;
                        
                        }
                        else if(monstro.get(0).isVivo()){
                        
                            lutar[1] = true;
                            lutar[2] = false;
                            lutar[3] = false;
                        
                        }
                    
                    }
                    else if(lutar[3]){
                    
                        if(monstro.get(0).isVivo()){
                        
                            lutar[3] = false;
                            lutar[1] = true;
                        
                        }
                        else if(monstro.get(1).isVivo()){
                        
                            lutar[2] = true;
                            lutar[1] = false;
                            lutar[3] = false;
                        
                        }
                    
                    }
                
                }
                            
            }            
                        
        }
        
        //Botão enter
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
        
            vezesEnter++;
            
            //Quando o jogador escolhe atacar
            if(escolha[1]){
            
                escolha[0] = false;
                escolha[1] = false;
                lutar[0] = true;
                
                //Coloca direto no monstro vivo
                for(int a = 1;a != monstro.size()+1;a++){
                        
                    if(monstro.get(a-1).isVivo()){
                            
                        lutar[a] = true;
                        break;
                            
                    }
                    else{
                            
                        lutar[a] = false;
                            
                    }
                        
                }
                                
            }
            
            //Se o jogador escolher mmagia
            if(escolha[2]){
            
                acoes.add(0, new Acoes(heroi));                
                escolha[0] = false;
                escolha[2] = false;                    
                ordenarAcao();
                iniciarAtaques = true;
                vezesEnter = 0;
                vezAcoes = -1;               
                
            }
            
            //Fugir
            if(escolha[3]){
            
                Random d = new Random();
                int c = d.nextInt(5);
                
                if(c == 1){
                
                    fugiu = true;
                    escolha[0] = false;
                    escolha[3] = false;
                    acaoFugir = true;
                    vezesEnter = 0;
                    
                }
                else{
                
                    fugiu = false;
                    escolha[0] = false;
                    escolha[3] = false;
                    acaoFugir = true;
                    vezesEnter = 0;
                    
                }
            
            }
            
            //Se não fugiu volta para o menu inicial    
            if(Naofugiu){
            
                vezAcoes = 0;
                vezesEnter = 0;
                iniciarAtaques = false;
                escolha[0] = true;
                escolha[1] = true;
                Naofugiu = false;
                
            }
            
            //Se o jogador escolher atacar algum monstro
            if(lutar[1] && vezesEnter == 2){
            
                //Guarda a ação para batalhar com o monstro
                guardarAcoes();      
                                            
                //Adiciona o efeito de batalha
                efeitosB[0] = true;
                
                ordenarAcao();
                iniciarAtaques = true;
                            
                lutar[0] = false;
                lutar[1] = false;
                vezesEnter = 0;
                fazerAtaque();
                
            }
            else if(lutar[2] && vezesEnter == 2){
            
                //Guarda a ação para batalhar com o monstro
                guardarAcoes();      
                                  
                //Adiciona o efeito de batalha
                efeitosB[1] = true;
                
                ordenarAcao();
                iniciarAtaques = true;
                
                lutar[0] = false;
                lutar[2] = false;
                vezesEnter = 0;
                fazerAtaque();
                
            }
            else if(lutar[3] && vezesEnter == 2){
            
                //Guarda a ação para batalhar com o monstro
                guardarAcoes();      
                          
                //Adiciona o efeito de batalha
                efeitosB[2] = true;
                
                ordenarAcao();
                iniciarAtaques = true;
                                                
                lutar[0] = false;
                lutar[3] = false;                
                vezesEnter = 0;
                fazerAtaque();
                
            }
            else if(iniciarAtaques){
                                
                vezAcoes++;
                fazerAtaque();
            
            }
            else if(batalhaTerminada || batalhaTerminada2){
            
                terminou = true;
            
            }            
            
            if(morto && vezesEnter == 1){
            
                System.exit(0);
            
            }
            
        }
        
        //Botão backSpace
        if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                                
            //Retrocede as vezes que enter é usado
            vezesEnter --;
            
            //Se enter for menor que 0, ele deixa enter igual a zero
            if(vezesEnter < 0){
                
                vezesEnter = 0;
            
            }
            
            //Volta a tela de opção anterior
            if(lutar[0]){
             
                //Deixa tudo como falso
                for(int a = 0;a < lutar.length; a++){
                                    
                    lutar[a] = false;
                
                }
                
                escolha[0] = true;
                escolha[1] = true;
                
            }
                    
        }
                
    }

    //Muda os efeitos da tela;
    private void iniciarLetras() {

        escolha[0] = true;//Sempre inicia com essa escolha
        escolha[1] = true;
        
        //Lista de escolhas
        Escolhas[0] = "Lutar";
        Escolhas[1] = "Curar";
        Escolhas[2] = "Correr";
        
        nomeMonstros = new String[monstro.size()];
        
        //lista de nome dos montros
        for(int a = 0; a < monstro.size(); a++){
        
            nomeMonstros[a] = monstro.get(a).getNome();
        
        }
        
    }
    
    /*
    Guarda as ações do jogador
    */
    private void guardarAcoes(){
    
        /*
        Ve qual o monstro que o jogador escolheu, e cria o objeto de ação
        */
        if(lutar[1]){
        
            acoes.add(new Acoes(monstro.get(0), heroi));
        
        }
        else if(lutar[2]){
        
            acoes.add(new Acoes(monstro.get(1), heroi));
        
        }
        else if(lutar[3]){
        
            acoes.add(new Acoes(monstro.get(2), heroi));
        
        }
        
    }
    
    
    //Faz a ação que o jogador planejou
    private String acaoDeBatalha(int acao){
    
        String mensagem = acoes.get(acao).fazer();
        
        return mensagem;
    
    }
    
    //Ordena para iniciar quem for o mais rápido
    private void ordenarAcao(){
        
        /*
        Confere se o monstro esta vivo, se estiver morto ele é escluído da lista
        */        
        for(int a = 0;a < lista.size(); a++){
        
            //Se o objeto não for vivo ele é excluído
            if(!lista.get(a).isVivo()){
            
                lista.remove(a);
                
            }
        
        }
        
        //Objeto personagem auxiliar
        Personagem aux;
        
        //Ordena os personagens, método buble sort, pois são poucos dados
        for(int a = 0;a < lista.size(); a++){
        
            for (int b = 0; b < lista.size();b++){
            
                if(lista.get(a).getSpe() > lista.get(b).getSpe()){
                
                    aux = lista.get(a);
                    lista.set(a, lista.get(b));
                    lista.set(b,aux);
                
                }
            
            }
        
        }                
       
    }  
    
    /*
    Faz os ataque guardados na lista
    */
    private void fazerAtaque(){
    
        if(vezAcoes < lista.size()){
        
            //Se for um personagem, a mensagem recebe o que o heroi fez
            if(lista.get(vezAcoes).getNome().equals(heroi.getNome())){
                
                //Toca o som primeiro e depois faz a ação
                if(acoes.get(0).isUsarCura()){
                
                    somCura();
                
                }
                else{
                
                    somEspada();
                
                }
    
                //Faz a ação do personagem                
                mensagensAposFazer = acoes.get(0).fazer();
                
                if(vezAcoes >= lista.size()){
                
                    acoes.clear();//Limpa a lista de ações
                                                            
                    vezAcoes = 0;
                    vezesEnter = 0;
                    iniciarAtaques = false;
                                        
                    conferirSeTerminou();
            
                    if(batalhaTerminada){
                    
                        escolha[0] = false;
                        escolha[1] = false;
                    
                    }
                    else{
                    
                        escolha[0] = true;                        
                        escolha[1] = true;                        
                        
                    }
                
                }                
                
            }
            else{
            
                //Se o monstro não estiver vivo, adiciona o evento para o próximo
                if(!lista.get(vezAcoes).isVivo()){
            
                    vezAcoes++;
                    
                }
                                     
                //Recebe o que o monstro faz
                if(vezAcoes < lista.size()){
                    
                    efeitoM = true;
                    somMonstro();
                    
                    //Ataca o herói
                    int dano = 0;
                    dano = (int) (lista.get(vezAcoes).getAtk() - heroi.getDef());
                    
                    if(dano < 0)dano = 0;
                    
                    heroi.setHp(heroi.getHp() - dano);
                    
                    //Se o personagem não morreu com o atk amostra a mensagem da quantidade de dano
                    if(heroi.isVivo()){
                    
                        mensagensAposFazer = heroi.getNome() + " recebeu " + String.valueOf(dano) + " de "+
                                             lista.get(vezAcoes).getNome();
                    
                    }
                    //Se o personagem morreu com o atk, amostra a mensagem de game over 
                    else{
                        
                        mensagensAposFazer = heroi.getNome() + " foi morto com o atk, fim de jogo.";
                        morto = true;                        
                        acoes.clear();//Limpa a lista de ações                                                            
                        vezAcoes = 0;
                        vezesEnter = 0;
                        iniciarAtaques = false;
                        
                    }
                    
                }
              
                //Limpa as ações e confere se a batalha terminou
                else{
            
                    acoes.clear();//Limpa a lista de ações
                                                            
                    vezAcoes = 0;
                    vezesEnter = 0;
                    iniciarAtaques = false;
                    
                    if(batalhaTerminada || batalhaTerminada2){
                    
                        escolha[0] = false;
                        escolha[1] = false;
                    
                    }
                    else{
                    
                        escolha[0] = true;                        
                        escolha[1] = true;                        
                        
                    }
                    
                }
                                               
            }
        
        }
        
        //Apos cada turno, confere se a batalha terminou
        conferirSeTerminou();
        
    }    

    /*
    Esse método confere se todos os monstros morreram e 
    inicia o que acontece antes da batalha terminar
    */
    private void conferirSeTerminou(){
    
        int cont = 0;
        
        for(int a = 0; a < monstro.size(); a++){
        
            //Se o monstro for morto
            if(!monstro.get(a).isVivo()){
            
                cont++;
                              
            }
        
        }
    
        if(cont == monstro.size()){
                        
            ganhos = new int[2];
            
            //Determina a quantidade de dinheiro ganho e experiencia
            for(int b = 0; b < monstro.size(); b++){
            
                heroi.setDinheiro(heroi.getDinheiro() + monstro.get(b).getDinheiro());
                ganhos[0] += monstro.get(b).getDinheiro();
                heroi.setExp(heroi.getExp() + monstro.get(b).getExp());
                ganhos[1] += monstro.get(b).getExp();
                
            }
            
            batalhaTerminada = true;
            evoluiu = heroi.evoluir();
            
            if(!heroi.isVivo()){
            
                System.exit(0);
            
            }
            
        }
        
    }
    
    //Efeito sonoro de cura
    private void somCura(){
      
        Musicas cura = new Musicas("/Musicas/cura.mp3");
        
    }
    
    //Efeito sonoro de som de espada
    private void somEspada(){
    
        Musicas espada = new Musicas("/Musicas/Espada.mp3");
    
    }

    //Efeito sonoro de som de ataque do monstro
    private void somMonstro(){
    
        Musicas monstrosSom = new Musicas("/Musicas/Monstro.mp3");
    
    }
    
    private void somCorrer(){
    
        Musicas correr = new Musicas("/Musicas/Correr.mp3");        
    
    }    
}
