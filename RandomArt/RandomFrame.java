import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Create a JFrame and call JPanel inside
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RandomFrame
{
    private JFrame theFrame;
    private RandomPanel thePanel;

    public RandomFrame(){
        theFrame = new JFrame();
        theFrame.setTitle("Group10 RandomArt");
        theFrame.setLocation(100,100);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        thePanel = new RandomPanel();
        theFrame.add(thePanel);

        // create panel with buttons
        JPanel buttonPanel = new JPanel();
        //Generate Button
        buttonPanel.add(getButton("RANDOM!"));

        //Position of the component
        theFrame.add(thePanel, BorderLayout.CENTER);
        theFrame.add(buttonPanel, BorderLayout.SOUTH);
        theFrame.pack();
    }

    public JButton getButton(String label){
        JButton result = new JButton(label);
        result.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    //Press to change the color
                    thePanel.setToColor();
                    //Repaint
                    thePanel.repaint();
                }
            });
        return result;
    }

    public void start(){
        theFrame.setVisible(true);
    }
}