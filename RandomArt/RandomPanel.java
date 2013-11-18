import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
public class RandomPanel extends JPanel {
    private Tree redNode;
    private Tree greenNode;
    private Tree blueNode;
    private String redFunc;
    private String greenFunc;
    private String blueFunc;
    public RandomPanel(){
        randomFunction();
    }
    public void printFunctions(){
        redFunc = redNode.returnString();
        greenFunc = greenNode.returnString();
        blueFunc = blueNode.returnString();
        
        System.out.println("RED :"+redFunc);
        System.out.println("GREEN :"+greenFunc);
        System.out.println("BLUE :"+blueFunc);
    }
    public void randomFunction(){
        int red = (int)(Math.random()*3);
        if(red == 0){
            redNode = new Sin((int)(Math.random()*8+4));
        }
        else if(red == 1){
            redNode = new Cos((int)(Math.random()*8+4));
        }
        else if(red == 2){
            redNode = new Average((int)(Math.random()*8+4));
        }
        
        int green = (int)(Math.random()*3);
        if(green == 0){
            greenNode = new Sin((int)(Math.random()*8+4));
        }
        else if(green == 1){
            greenNode = new Cos((int)(Math.random()*8+4));
        }
        else if(green == 2){
            greenNode = new Average((int)(Math.random()*8+4));
        }
        
        int blue =(int)(Math.random()*3);
         if(blue == 0){
            blueNode = new Sin((int)(Math.random()*8+4));
            
        }
        else if(blue == 1){
            blueNode = new Cos((int)(Math.random()*8+4));
        }
        else if(blue == 2){
            blueNode = new Average((int)(Math.random()*8+4));
        }
       }
    public String returnFuctionR(){
        return redFunc;
    }
    public String returnFuctionG(){
        return greenFunc;
    }
    public String returnFuctionB(){
        return blueFunc;
    }
    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i = 0; i < getWidth(); i++){
            for(int j = 0; j < getHeight(); j++ ){
                double x = ((((double) i) / getWidth()) * 2.0) - 1.0;
                double y = ((((double) j) / getHeight()) * 2.0) - 1.0;
                double r = redNode.returnDouble( x,  y);
                double gr = greenNode.returnDouble( x,  y);
                double b = blueNode.returnDouble( x,  y);
                g2.setColor(new Color(rgb(r),rgb(gr),rgb(b)));
                g2.fillRect(i, j, i, j);
            }
        }
    }
    public int rgb(double x){
        return (int)((x+1)*255/2);
       }
}
