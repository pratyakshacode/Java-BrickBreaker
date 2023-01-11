import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InnerScreen extends JPanel implements ActionListener, KeyListener{

    private int playerX = 250;
    private int playerY = 640;
    private int ballX = 200;
    private int ballY = 200;
    private int score = 0;
    private int ballXPos = 10;
    private int ballYPos = 10;
//    private int startCount = 0;
    private int totalBricks = 21;


    private BricksCage brickSet;

    private boolean start = false;

    Timer timer;

    public InnerScreen(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(0, this);
        timer.start();

        brickSet = new BricksCage(3,7);
    }


    public void paint(Graphics g){

        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 670);

        g.setColor(Color.blue);
        g.fillOval(ballX, ballY, 20, 20);


        g.setColor(Color.MAGENTA);
        g.fillRect(playerX, playerY, 100, 5);

        //bricks set

        brickSet.drawBricks((Graphics2D) g);

        // score of the game

        g.setColor(Color.ORANGE);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Score : " + score, 490,30);

        if(ballY >= 650){
            start = false;
            ballX = 30;
            ballY = 250;

            g.setColor(Color.CYAN);
            g.setFont(new Font("Algerian", Font.BOLD, 30));
            g.drawString("Game Over", 200, 320);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.setColor(Color.yellow);
            g.drawString("Press Enter to Restart", 190,350);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(start){

            if(ballX <= 0) ballXPos = - ballXPos;
            if(ballX >= 570) ballXPos = - ballXPos;
            if(ballY <= 0) ballYPos = -ballYPos;

            ballX -= ballXPos;
            ballY -= ballYPos;
            repaint();

        }

        Rectangle ballBox = new Rectangle(ballX, ballY, 20,20);
        Rectangle playerBox = new Rectangle(playerX, playerY-10, 100,20);

        if(ballBox.intersects(playerBox)) ballYPos = -ballYPos;

        Outer : for(int i=0; i<brickSet.bricks.length; i++){
            for(int j=0; j<brickSet.bricks[0].length; j++){

                if(brickSet.bricks[i][j] == 1){

                    int width = brickSet.brickWidth;
                    int height = brickSet.brickHeight;

                    int bricksXPos = 23 + j * width;
                    int bricksYPos = 50 + i * height;

                    Rectangle bricksBox = new Rectangle(bricksXPos, bricksYPos, width, height);

                    if(ballBox.intersects(bricksBox)){
                        brickSet.setBrick(0, i, j);
                        totalBricks--;

                        if(ballX + 19 <= bricksXPos || ballX + 1 >= bricksXPos + width){
                            ballXPos = -ballXPos;
                        }
                        else{
                            ballYPos = - ballYPos;
                        }

                        score += 10;

                        break Outer;
                    }
                }
            }
        }

    }



    private void moveLeft(){

        if(playerX <= 0)
            playerX = 0;
        else
            playerX -= 20;
    }

    private void moveRight(){

        if(playerX >= 495)
            playerX = 495;
        else
            playerX += 20;
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            start = true;
            moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            start = true;
            moveRight();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!start){
                score = 0;
                totalBricks = 21;
                ballXPos = -1;
                ballYPos = -2;
                brickSet = new BricksCage(3, 7);


            }
        }

        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
