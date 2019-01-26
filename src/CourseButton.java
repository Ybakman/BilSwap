/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import javax.swing.*;
import java.awt.*;
public class CourseButton extends JButton
{
   /**
   * This constructor creates a course button
   * @param s which is buttonName
   */
  public CourseButton(String s)
  {
    setPreferredSize(new Dimension(500,500));
    setText(s);
  }
}  
    