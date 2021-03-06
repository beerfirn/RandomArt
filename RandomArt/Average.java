
/**
 * Write a description of class Average here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Average extends Tree
{
    private double i;
    private double j;
    private Tree nextTree;
    private Tree subNode;
    /**
     * Constructor 
     */
    public Average(int level)
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
        return (nextTree.returnDouble(x,y)+ subNode.returnDouble(x,y))/2 ;
    } 
    public String returnString(){
        return "Avg("+nextTree.returnString()+ subNode.returnString()+")";
    }
}
