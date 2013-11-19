import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RandomFrame 
{
    private JFrame theFrame;
    private RandomPanel thePanel;
    public RandomFrame(){
        //Create Frame
        theFrame = new JFrame();
        theFrame.setTitle("Group10 RandomArt");
        theFrame.setLocation(1000,400); //set where the frame will appear on monitor
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setPreferredSize(new Dimension(400,400)); //border's size
 
        thePanel = new RandomPanel();
        theFrame.add(thePanel);

        //create panel with buttons
        JPanel buttonPanel = new JPanel();
        //Generate Button
        buttonPanel.add(getButton("RANDOM!")); //generate new color
        buttonPanel.add(getButton2("EQUATION")); //print out equation
       
        theFrame.add(thePanel, BorderLayout.CENTER);
        theFrame.add(buttonPanel, BorderLayout.SOUTH);
        theFrame.pack();
    }
    public JButton getButton(String label){
        JButton result = new JButton(label);
        result.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                    //Press to change the color
                    thePanel.randomFunction();
                    //Repaint
                    thePanel.repaint();
                   }
                });
        return result;
    }
    public JButton getButton2(String label){
        JButton result = new JButton(label);
        result.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                    thePanel.printFunctions(); //make Equation Button work
                   }
                });
        return result;
    }
    public void start(){
        theFrame.setVisible(true); // make theframe can see
    }
}
