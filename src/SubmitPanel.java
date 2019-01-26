/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 

import javax.swing.*;
import java.awt.*;
public class SubmitPanel extends JPanel
{
  JButton submitButton;
  
   /**
   * This contructor creates a submit panel
   */
  public SubmitPanel()
  {
    submitButton = new JButton("SUBMIT YOUR REQUEST");
    submitButton.setPreferredSize(new Dimension(200,70));
    //submitButton.setBorderPainted(false);
    submitButton.setBackground(new Color(183,15,10));
    //submitButton.setOpaque(true);
    submitButton.setForeground(Color.white);
    add(submitButton,BorderLayout.CENTER);
  }
  
  public JButton getButton()
  {
    return submitButton;
  }  
}  
    