import java.util.Stack;

/**
 * Write a description of class RandomExpression here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
class RandomExpression{
    
    private static final String OPERATORS = "SSSSCCCCAMR";
    private static final String OPERANDS = "xy";
    // functions that take a single operand
    private static final String SINGLE_OPERAND_OPERATORS = "SCQ";

    private static final int NUM_SINGLE_OPERAND_FUNCTIONS = countSingleOperandFunctions();

    
    // Currently single operance functions are S for sin and
    // C for cosine
    private static int countSingleOperandFunctions() {
        int total = 0;
        for(int i = 0; i < OPERATORS.length(); i++){
            char ch = OPERATORS.charAt(i);
            if(SINGLE_OPERAND_OPERATORS.indexOf(ch) != -1)
                total++;
        }
        return total;
    }
    
    // probability that operand will be another expression
    // instead of a primitive
    private final double PROBABILITY_DEEPER;


    // String representation of expression. Shown is postfix
    // notation to make for easier evaluation
    private final String randExpression;

    // higher number means more complex
    // lowest allowed value = 0
    private final int EXPRESSION_COMPLEXITY; 

    private static final int DEFAULT_MAX_COMPLEXITY = 5;;
    private static final double DEFAULT_PROBABILITY_USE_OPERATOR_FOR_OPERAND = 0.85;
    
    // create a new Random Expression
    // with probabilityDeeper = 0.8
    // and expressionComplexity = 10
    public RandomExpression(){
        this(DEFAULT_MAX_COMPLEXITY, DEFAULT_PROBABILITY_USE_OPERATOR_FOR_OPERAND);
    }

    // pre: complexity >= 0, 0 <= deeper <= 1.0
    // higher values for complexity and deeper lead to
    // more complex expresions
    public RandomExpression(int complexity, double deeper){
        EXPRESSION_COMPLEXITY = complexity;
        PROBABILITY_DEEPER = deeper;
        randExpression = createExpression(0);
    }

    // a way to create a hard coded expression
    public RandomExpression(String s){
        EXPRESSION_COMPLEXITY = -1;
        PROBABILITY_DEEPER = -1;        
        randExpression = s;
    }


    private String createExpression(int currentLevel){
        int op = (int)(Math.random() * OPERATORS.length());
        int oper1 = (int)(Math.random() * 2);
        int oper2 = (int)(Math.random() * 2);
        String result = OPERATORS.substring(op, op + 1);
        boolean deeperFirstOperand = Math.random() < PROBABILITY_DEEPER;
        boolean deeperSecondOperand = Math.random() < PROBABILITY_DEEPER;

        // single operand operators
        if( op < NUM_SINGLE_OPERAND_FUNCTIONS){
            // base case, operands are simple values, x or y
            if(!deeperFirstOperand || currentLevel == EXPRESSION_COMPLEXITY){
                result = OPERANDS.charAt(oper1) +  result;
            }
            // recursive case, operand is another expression
            else{
                result = createExpression(currentLevel + 1) +  result;
            }
        }
        else{
            // base case, operands are simple values, x or y
            if(currentLevel == EXPRESSION_COMPLEXITY || (!deeperFirstOperand && !deeperSecondOperand)){
                result = OPERANDS.charAt(oper1) + "" + OPERANDS.charAt(oper2) + result;
            }
            // first opearnd is simple value, second is another expression
            else if(!deeperFirstOperand){
                result = OPERANDS.charAt(oper1) +  createExpression(currentLevel + 1) + result;
            }
            // second opearnd is simple value, first is another expression
            else if(!deeperSecondOperand){
                result = createExpression(currentLevel + 1) + OPERANDS.charAt(oper2) + result;
            }
            // both operands are complex expressions
            else{
                result = createExpression(currentLevel + 1) + createExpression(currentLevel + 1) + result;
            }
        }
        return result;
    }

    // called to get result of expression at a given
    // value of x and y.
    // pre: -1.0 <= x <= 1.0, -1.0 <= y <= 1.0, 
    // post: return a value between -1.0 and 1.0, inclusive
    public double getResult(double x, double y){
        Stack<Double> operands = new Stack<Double>();
        for(int i = 0; i < randExpression.length(); i++){
            char ch = randExpression.charAt(i);
            if(ch == 'x')
                operands.push(x);
            else if(ch == 'y')
                operands.push(y);
            else{
                // operator
                double op1 = operands.pop();
                if(ch == 'S')
                    operands.push(Math.sin(Math.PI * op1));
                else if(ch == 'C')
                    operands.push(Math.cos(Math.PI * op1));
                else if(ch == 'M')
                    operands.push(op1 * operands.pop());
                else if (ch == 'A')
                    operands.push(ave(op1, operands.pop()));
                else if( ch == 'Q')
                    operands.push(Math.sqrt(Math.abs(op1)));
                // add else if branch for new operators here
                else if( ch == 'R'){
                    double result = 0.5;
                    if(op1 != 0.0)
                        result = operands.pop() % op1;
                    else
                        operands.pop();
                    operands.push(result);
                }   
            }
        }
        assert operands.size() == 1 : operands.size();
        double result = operands.pop();
        result = (result < -1.0) ? -1.0 : (result > 1.0) ? 1.0 : result;
        assert -1.0 <= result && result <= 1.0 : result;
        return result;
    }

    private static double ave(double x, double y){
        return (x + y) / 2.0;
    }

    public String toString(){
        return randExpression;
    }

    // from random art, test method
    public static double getValExp(double x, double y){
        return Math.sin(Math.PI * Math.sin(Math.PI * Math.sin(Math.PI * (Math.sin(Math.PI * Math.sin(Math.PI * Math.sin(Math.PI * Math.sin(Math.PI * Math.cos(Math.PI * y))))) * Math.cos(Math.PI * Math.sin(Math.PI * Math.cos(Math.PI * ave(Math.sin(Math.PI * y), (x * x)))))))));
    }

    // simple by hand test
    public static double getValueHardCoded(double x, double y){
        double pi = Math.PI;
        return Math.sin(pi * Math.cos(pi * Math.cos(pi * Math.sin(pi * ave(Math.cos(pi * y),y) * Math.sin(pi * x * y )))));
    } 
}