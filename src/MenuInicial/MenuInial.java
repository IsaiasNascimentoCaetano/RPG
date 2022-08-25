package MenuInicial;

import java.awt.Dimension;
import Painel.Painel;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import Musica.Musicas;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class MenuInial extends javax.swing.JFrame {

    private boolean opcoes[];
    private Musicas musicaFundo;   
    private BufferedImage fundo;
    
    public MenuInial() {
        
        initComponents();
        
        try{
        
            InputStream file = getClass().getResourceAsStream("");
            fundo = ImageIO.read(file);
            
        }
        catch(IOException ex){
        
            System.err.println(ex);
        
        }
        
        //Cria o diretório se ele não exitir
        File local = new File("c:\\Save");
        
        if(!local.exists()){
        
            local.mkdir();
            
        }
        
        //Diminuir o caminho das músicas
        musicaFundo = new Musicas("/Musicas/MusicaMenu.mp3", true);
        
        this.setSize(new Dimension(Painel.largura,Painel.altura));
        this.setFocusable(true);
        
        opcoes = new boolean[3];
        
        jButton1.setBorder(null);
        jButton1.setBackground(new Color(1,1,1,0));
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setOpaque(false);
        
        jButton4.setBorder(null);
        jButton4.setBackground(new Color(1,1,1,0));
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setOpaque(false);
        
        jButton5.setBorder(null);
        jButton5.setBackground(new Color(1,1,1,0));
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setOpaque(false);
        
        opcoes[0] = true;
        
        conferir();
                        
    }

    private void conferir(){
    
        if(opcoes[0]){
        
            //Muda o texto
            jButton4.setText("-> Novo jogo <-");            
            jButton1.setText("Carregar jogo");
            jButton5.setText("Sobre");
        
            //Muda a cor
            jButton4.setForeground(new Color(102, 205, 170));
            jButton1.setForeground(Color.WHITE);
            jButton5.setForeground(Color.WHITE);
            
        }
        else if(opcoes[1]){
        
            jButton4.setText("Novo jogo");
            jButton1.setText("-> Carregar jogo <-");            
            jButton5.setText("Sobre");
        
            jButton1.setForeground(new Color(102, 205, 170));
            jButton5.setForeground(Color.WHITE);
            jButton4.setForeground(Color.WHITE);
            
        }
        else{
        
            jButton4.setText("Novo jogo");
            jButton1.setText("Carregar jogo");
            jButton5.setText("-> Sobre <-");
            
            jButton5.setForeground(new Color(102, 205, 170));
            jButton4.setForeground(Color.WHITE);
            jButton1.setForeground(Color.WHITE);
            
        }
    
    }
    
    private void iniciar(){
    
        //inicia novo jogo
        if(opcoes[0]){
            
            TelaNome nome = new TelaNome(musicaFundo);
            nome.setVisible(true);
            
            this.dispose();
            
        }
        //Carrega jogo
        else if(opcoes[1]){
        
            //Cria a tela
            JFrame tela = new JFrame("Teste");          
            Painel painel = new Painel(true);
            tela.add(painel);        
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tela.setResizable(false);        
            tela.pack();
            tela.setLocationRelativeTo(null);                
            tela.setVisible(true);

            try{
                //Aguarda um tempo, caso o objeto musica não for carregado
                Thread.sleep(400);
                
            }
            catch(InterruptedException e){
            
                System.err.println(e);
            
            }
            
            musicaFundo.parar();
            
            this.dispose();
        
        }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jButton1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Carregar Jogo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Novo jogo");

        jButton5.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Sobre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(333, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        if(evt.getKeyCode() == KeyEvent.VK_S){
        
            if(opcoes[0]){
            
                opcoes[0] = false;
                opcoes[1] = true;
            
                conferir();
                
            }
            else if(opcoes[1]){
            
                opcoes[1] = false;
                opcoes[2] = true;
            
                conferir();
                
            }
            else if(opcoes[2]){
            
                opcoes[2] = false;
                opcoes[0] = true;
            
                conferir();
                
            }
        
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_W){
        
            if(opcoes[0]){
            
                opcoes[0] = false;
                opcoes[2] = true;
            
                conferir();
                
            }
            else if(opcoes[1]){
            
                opcoes[1] = false;
                opcoes[0] = true;
            
                conferir();
                
            }
            else if(opcoes[2]){
            
                opcoes[2] = false;
                opcoes[1] = true;
            
                conferir();
                
            }
        
        }
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        
            iniciar();
        
        }
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
     
    }//GEN-LAST:event_formKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
