
/**
 * Write a description of class Sin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sin extends Tree
{
    private double i;
    private double j;
    private Tree nextTree;
    private Tree subNode;
    /**
     * Constructor for objects of class Sin
     */
    public Sin(int level)
    {
       i = (int)(Math.random()*3);
        if(level > 1){
            if(i==0){
                nextTree = new Sin(level-1);
                subNode = new Sin(level-1);
            }
            if(i==1){
                nextTree = new Cos(level-1);
                subNode = new Cos(level-1);
            }
            if(i==2){
                nextTree = new Average(level-1);
                subNode = new Average(level-1);
            }
        }else{
            j = (int)(Math.random()*2);
            if(j==0){
                nextTree = new VariableX();
                subNode = new VariableX();
            }
            if(j==1){
                nextTree = new VariableY();
                subNode = new VariableY();
            }
        }
    }
    public double returnDouble(double x,double y){     
        return Math.sin(Math.PI*nextTree.returnDouble(x,y));
    }
    public String returnString(){
        return "sin("+nextTree.returnString()+")";
    }
}
