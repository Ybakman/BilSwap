/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import javax.swing.*;
import java.awt.*;
public class LastPanel extends JPanel
{
  RequestPanel panel;
  
   /**
   * This constructor creates the last form of the make request panel by given user
   * @param user
   */
  public LastPanel(User user)
  {
    panel = new RequestPanel(user);
    JLabel label = (new JLabel("COURSE YOU WANT TO OFFER                                        COURSE YOU WANT TO TAKE"));
    label.setFont(new Font("Serif", Font.PLAIN, 20));
    label.setForeground(new Color(183,15,10));
    add(label, BorderLayout.NORTH);
    add(panel, BorderLayout.CENTER);
  }
}

      
  