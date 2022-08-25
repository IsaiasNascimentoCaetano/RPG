package novojogo;

import javax.swing.JFrame;

/*Classe principal:
*Esta classe ter por objetivo, iniciar o jogo. 
*Apenas isso
*/
public class NovoJogo {

    static JFrame f = null;
    
    public static void main(String[] args) {
        
        new FundoPreto().setVisible(true);
        
        f = new JFrame();
        Introducao i = new Introducao();
        f.add(i);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
    }
    
}
