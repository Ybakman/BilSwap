import com.mongodb.client.MongoDatabase;
import javax.swing.*;
public class PanelChecker
{
  public static void main(String [] args)
  {
    JFrame frame = new JFrame();
    frame.setSize(800,700);
    frame.setTitle("try");
    new DbAction();
    //User user = new User("Revegantor", "Babasonlar23","faruk.bakman@ug.bilkent.edu.tr");
    //System.out.println(user);
    JPanel panel = new SettingsPageGUI();
    frame.add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
    //Request sayısını check etmeyi unutma
  }
}  