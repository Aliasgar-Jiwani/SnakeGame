 
package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,KeyListener{
 boolean left=false;
  boolean right=true;
   boolean up=false;
    boolean down=false;
    boolean gameOver=false;
    
     int[] snakeXlength=new int[750];
      int[] snakeYlength=new int[750];
      int lengthOfSnake=3;
      int moves=0;
      Timer timer;
    
      
     int delay=150;
     int enemyX,enemyY;
     int score=0;
     int xpos[]={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
     int ypos[]={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private ImageIcon snakeTitle =new ImageIcon(getClass().getResource("snaketitle.jpg"));
     private ImageIcon snakeLeft =new ImageIcon(getClass().getResource("leftmouth.png"));
      private ImageIcon snakeRight =new ImageIcon(getClass().getResource("rightmouth.png"));
       private ImageIcon snakeUp =new ImageIcon(getClass().getResource("upmouth.png"));
        private ImageIcon snakeDown =new ImageIcon(getClass().getResource("downmouth.png"));
        private ImageIcon snakeImage =new ImageIcon(getClass().getResource("snakeimage.png"));
        private ImageIcon enemyImage =new ImageIcon(getClass().getResource("enemy.png"));
       
        
    public GamePanel()
    {
        
      timer=new Timer(delay, this);
      timer.start();
   this.addKeyListener(this);
   this.setFocusable(true);
   this.setFocusTraversalKeysEnabled(true);
   newEnemy();
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        //title
        g.setColor(Color.GREEN);
        g.drawRect(24, 10, 854, 55);
        g.drawRect(24, 74, 851, 576);
        snakeTitle.paintIcon(this,g,26,11);
        //body
        g.setColor(Color.BLACK);
        g.fillRect(25, 75,850, 575);
        //moves
        if(moves==0){
            
            snakeXlength[0]=100;
            snakeXlength[1]=75;
            snakeXlength[2]=50;
            
             snakeYlength[0]=100;
             snakeYlength[1]=100;
             snakeYlength[2]=100;
             
             
        }

        if(left){
            snakeLeft.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);       
        }
        if(right)
        {
             snakeRight.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);       
        }
        if(up)
        {
             snakeUp.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);       
        }
        if(down)
        {
             snakeDown.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);       
        }
        for (int i = 1; i <lengthOfSnake; i++) {
             snakeImage.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);  
            
        }
         enemyImage.paintIcon(this,g,enemyX,enemyY);
         g.setFont(new Font("Arial",Font.BOLD,20));
         g.setColor(Color.green);
         g.drawString("Score:"+score,750,45);
         if(gameOver)
         {
              g.setColor(Color.red);
              g.setFont(new Font("Arial",Font.BOLD,40)); 
               g.drawString("Game Over",350,290);
                 g.drawString("Press Space To Restart",230,340);
                 
              
               
         }
      
    g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = lengthOfSnake; i > 0; i--) {
            snakeXlength[i]=snakeXlength[i-1];
            snakeYlength[i]=snakeYlength[i-1];
            
        }
        
        if(left)
        {
            
            snakeXlength[0]=snakeXlength[0]-25;
        }
          if(right)
        {
            snakeXlength[0]=snakeXlength[0]+25;
        }
                   if(up)
        {
            snakeYlength[0]=snakeYlength[0]-25;
        }
              if(down)
        {
            snakeYlength[0]=snakeYlength[0]+25;
        }
              //right
              if(snakeXlength[0]>850)
              {
                  snakeXlength[0]=25;
      
              }
              //left
              if(snakeXlength[0]<25)
              {
                  snakeXlength[0]=850;
              }
              //up
               if(snakeYlength[0]>625)
              {
                  snakeYlength[0]=75;
              }
               //down
               if(snakeYlength[0]<75)
              {
                  snakeYlength[0]=625;
              }
                collidewithEnemy();
                collidewithbody();
                repaint();
               
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
   //restart
   if(gameOver)
   {
    if(e.getKeyCode()==KeyEvent.VK_SPACE){
        restart();
    }
   }
//left turn

    if(e.getKeyCode()==KeyEvent.VK_LEFT &&!right){

        left=true;
    right=false;
    up=false;
    down=false;
moves++;
    }

 //rightturn

  if(e.getKeyCode()==KeyEvent.VK_RIGHT&& !left){
   
    left=false;
    right=true;
    up=false;
    down=false;
    moves++;

 }
 
//upturn

if(e.getKeyCode()==KeyEvent.VK_UP&&!down){
    left=false;
    right=false;
    up=true;
    down=false;
moves++;

}

//down turn

if(e.getKeyCode()==KeyEvent.VK_DOWN&&!up){
    left=false;
    right=false;
    up=false;
    down=true;
moves++;

}

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void newEnemy() {
    Random random=new Random();      
   enemyX=xpos[random.nextInt(34)];
   enemyY=ypos[random.nextInt(23)];
   
        for (int i = lengthOfSnake; i > 0; i--) {
            if(snakeXlength[i]==enemyX&&snakeYlength[i]==enemyY)
            {
                newEnemy();
            }
        }
    }

    private void collidewithEnemy() {
       if(snakeXlength[0]==enemyX&&snakeYlength[0]==enemyY)
       {
           lengthOfSnake++;
           score=score+10;
           newEnemy();
       }
    }

    private void collidewithbody() {
        for (int i = lengthOfSnake; i >0; i--) {
            if(snakeXlength[0]==snakeXlength[i] && snakeYlength[0]==snakeYlength[i])
            {
                timer.stop();
                gameOver=true;
            }
        }
    }
   
    public void restart(){
        lengthOfSnake=3;
        moves=0;
        left=false;
        right=true;
        up=false;
        down=false;
        gameOver=false;
        score=0;     
        timer.start();
        repaint();
    }
   
}
