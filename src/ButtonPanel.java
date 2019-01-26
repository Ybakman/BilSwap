/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.*;
import javax.imageio.*;

public class ButtonPanel extends JPanel 
{
  
  JButton button;
  
   /**
   * This constructor creates a new button panel with given button
   * @param buttonName
   */
  public ButtonPanel(String buttonName) {
    button = new JButton();
    if (buttonName.equals("Make a Swap Request")) {
      try {
        Image img = ImageIO.read(getClass().getResource("try.png"));
        ImageIcon icon = new ImageIcon(img);
        button.setText(buttonName);
        button.setIcon(icon);
        button.setIconTextGap(15);
      } catch (Exception ex) {
        System.out.println("catched");
      }
    }
    if (buttonName.equals("Your Swap Requests")) {
      try {
        Image img = ImageIO.read(getClass().getResource("change.png"));
        ImageIcon icon = new ImageIcon(img);
        button.setText(buttonName);
        button.setIcon(icon);
        button.setIconTextGap(15);
      } catch (Exception ex) {
        System.out.println("catched");
      }
    }
    if (buttonName.equals("Profile Settings")) {
      try {
        Image img = ImageIO.read(getClass().getResource("settings.png"));
        ImageIcon icon = new ImageIcon(img);
        button.setText(buttonName);
        button.setIcon(icon);
        button.setIconTextGap(15);
      } catch (Exception ex) {
        System.out.println("catched");
      }
    }
    if (buttonName.equals("What is BilSwap")) {
      try {
        Image img = ImageIO.read(getClass().getResource("info.png"));
        ImageIcon icon = new ImageIcon(img);
        button.setText(buttonName);
        button.setIcon(icon);
        button.setIconTextGap(15);
      } catch (Exception ex) {
        System.out.println("catched");
      }
    }
    button.setPreferredSize(new Dimension(280, 100));
    button.setFont(new Font("Serif", Font.BOLD, 20));
    button.setBackground(new Color(20, 100, 246));
    button.setForeground(Color.white);
    add(button);
  }
  
   /**
   * This method gives panel's button
   * @return button
   */
  public JButton getButton() {
    return button;
  }
  
}
