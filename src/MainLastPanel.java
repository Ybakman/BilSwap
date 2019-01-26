
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class MainLastPanel extends JPanel
{
  
  public MainLastPanel(User user)
  {
    JPanel mainPanel = new MainPanel(user);
    add(mainPanel, BorderLayout.CENTER);
  
  }
  
}  