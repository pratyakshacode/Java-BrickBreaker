import java.awt.*;

public class BricksCage {

    public int bricks[][];
    public int brickWidth;
    public int brickHeight;

    public BricksCage(int row, int col){

        bricks = new int[row][col];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                bricks[i][j] = 1;
            }
        }

        brickWidth = 540 / col;
        brickHeight = 140 / row;
    }

    public void setBrick(int val, int row, int col){
        bricks[row][col] = 0;
    }

    public void drawBricks(Graphics2D g){
        for (int i=0; i<bricks.length; i++){
            for(int j=0; j<bricks[0].length; j++){

                if(bricks[i][j] == 1){

                    g.setColor(Color.white);

                    g.fillRect(j*brickWidth+23, i*brickHeight+50, brickWidth, brickHeight);

                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickWidth+23, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }
}
