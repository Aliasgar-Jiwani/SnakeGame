
package snake;

import java.awt.Color;
import javax.swing.JFrame;


public class Snake extends JFrame{
    
    public Snake()
    {
        this.setBounds(10,20,905,700);
        GamePanel panel=new GamePanel();
        panel.setBackground(Color.DARK_GRAY);
        this.add(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        Snake s=new Snake();
    }
}
