import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
/**
 * Class to print the random color and check grey or color.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class RandomPanel extends JPanel{
    private static final int NUM_SHADES = 256;
    private Color[] grayscales;
    private boolean color;
    private RandomExpression exp;
    private RandomExpression[] colorExp;

    // indices into colorExp array
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    int r, gr, b;
    int shade;

    // create 256 gray scale colors to avoid lots
    // of garbage color objects
    public RandomPanel(){
          
        setPreferredSize(new Dimension(400,400));
        //exp = new RandomExpression(); 
        //exp = new RandomExpression("xxACSSxCAyCyxASASCAyCCAyyyAAxMSxCxCAxSySMMCMCSMSCS");

        // if using 3 different functions for color
        colorExp = new RandomExpression[3];  
        setToColor();
    }

    /**public void setToGray(){
        exp = new RandomExpression();
        color = false;
    } **/

    public void setToColor(){
        for(int i = 0; i < colorExp.length; i++)
            colorExp[i] = new RandomExpression();
        color = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        RandomExpression randExp1 = new RandomExpression();
        RandomExpression randExp2 = new RandomExpression();
        RandomExpression randExp3 = new RandomExpression();
        
        for(int i = 0 ; i < getWidth() ; i++){
          for(int j = 0 ; j < getHeight() ; j++){
            double x = ((((double) i)/getWidth())*2.0)-1.0;
            double y = ((((double) j)/getHeight())*2.0)-1.0;
            int r = (int)((randExp1.getResult(x,y)+1)*(255/2));
            int b = (int)((randExp2.getResult(x,y)+1)*(255/2));
            int gr = (int)((randExp3.getResult(x,y)+1)*(255/2));
            g2.setColor(new Color(r,gr,b)) ;        
            g2.fillRect(i,j,1,1);
          }
        }

        System.out.println("red: " + randExp1);
        System.out.println("green: " + randExp2);
        System.out.println("blue: " + randExp3);
    }


    private int getShade(double x, double y, RandomExpression exp){
        double val = exp.getResult(x, y);
        int result = (int)((val + 1.0) / 2.0 * NUM_SHADES);
        result = (result == 256) ? 255 : result;
        assert 0 <= result && result < NUM_SHADES : result + " " + val;
        return result;
    }

}