/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 * This is the panel where users are displayed in chatroom page
 */ 


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Alperen
 */
public class ChatWestPanel extends JPanel {
  
  private ImageIcon personImage = new ImageIcon(getClass().getResource("personImage.png"));
  private ImageIcon onlineImage = new ImageIcon(getClass().getResource("onlineImage.png"));
  private ImageIcon offlineImage = new ImageIcon(getClass().getResource("offlineImage.png"));
  private ArrayList<Boolean> checkExist = new ArrayList<Boolean>();
  
  /**
   * Creating the panel by getting users and avalibilty of users from ChatRoom
   * object
   *
   * @param chatRoom takes a ChatRoom object
   */
  public ChatWestPanel(ChatRoom chatRoom) { 
    this.setLayout(new GridLayout(10, 0, 10, 10));
    this.setPreferredSize(new Dimension(260, 700));
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    //Initialize checkExist
    for(int i = 0; i<chatRoom.getUsers().size(); i++)
    {
      checkExist.add(false);
    } 
    for(int i = 0; i<chatRoom.getUsers().size(); i++)
    {
      for(int k =i-1; k>=0; k--)
      {
        if(chatRoom.getUsers().get(i).equals(chatRoom.getUsers().get(k)))
        {
          checkExist.set(i, true);
        }  
      }  
    }  
    
    //Header of the panel
    JPanel headerP = new JPanel(new BorderLayout());
    headerP.setBorder(BorderFactory.createLineBorder(Color.black));
    JLabel headerL = new JLabel("USERS");
    headerL.setFont(new Font("Serif", Font.PLAIN, 30));
    headerL.setForeground(new Color(183,15,10));
    headerP.add(headerL, BorderLayout.CENTER);
    headerL.setHorizontalAlignment(JLabel.CENTER);
    headerL.setVerticalAlignment(JLabel.CENTER);
    this.add(headerP);
    ArrayList<String> users = chatRoom.getUsers();
    ArrayList<Boolean> availability = chatRoom.getAvailabilityOfUsers();
    
    //adding users
    for (int i = 0; i < users.size(); i++) 
    {
      if(!checkExist.get(i))
      {  
        JLabel personIcon = new JLabel(personImage);
        personIcon.setPreferredSize(new Dimension(50, 50));
        JLabel userL = new JLabel(users.get(i));
        userL.setFont(new Font("Serif", Font.PLAIN, 15));
        userL.setPreferredSize(new Dimension(100, 50));
        if (availability.get(i) == true) {
          JLabel onlineStatus = new JLabel(onlineImage);
          onlineStatus.setPreferredSize(new Dimension(50, 50));
          JPanel userP = new JPanel(new GridLayout(0, 3));
          userP.add(personIcon);
          userP.add(userL);
          userP.add(onlineStatus);
          this.add(userP);
        } else {
          JLabel onlineStatus = new JLabel(offlineImage);
          onlineStatus.setPreferredSize(new Dimension(50, 50));
          JPanel userP = new JPanel(new GridLayout(0, 3));
          userP.add(personIcon);
          userP.add(userL);
          userP.add(onlineStatus);
          this.add(userP);
        }
      }
    }  
  }
}
