/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Date;


import java.awt.*;

import java.text.SimpleDateFormat;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;

/**
 * This is the panel that contains messageBox and send button. It allows user to
 * send a new message
 *
 * @author Alperen
 */
public class ChatSouthPanel extends JPanel {
  
  JTextArea messageBox;
  ChatRoom chatRoom;
  User user;
  ArrayList<Message> messageList;
  MongoDatabase db;
  MongoCollection<Document> collection;
  public static Timer timer;
  JTextArea chatBox;
  
  /**
     * 
     * @param chatRoom
     * @param user
     * @param chatBox
     */
  public ChatSouthPanel(ChatRoom chatRoom, User user, JTextArea chatBox) {
    MyListener listener = new MyListener();
    this.chatBox = chatBox;
    timer= new Timer(50,listener);
    timer.start();
    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.chatRoom = chatRoom;
    this.user = user;
    // interacting with database
    db = DbAction.database;
    collection = db.getCollection("ChatRooms");
    //Message box
    messageBox = new JTextArea();
    messageBox.setPreferredSize(new Dimension(620, 100));
    messageBox.setFont(new Font("Serif", Font.PLAIN, 30));
    messageBox.setText("Type Your Message...");
    MouseListener messageListener = new MessageListener();
    messageBox.addMouseListener(messageListener);
    KeyListener messageListener1 = new MessageKeyListener();
    messageBox.addKeyListener(messageListener1);
    //send button
    JButton sendbtn = new JButton("Send Message");
    sendbtn.setPreferredSize(new Dimension(150, 100));
    sendbtn.setBackground(new Color(20,100,246));
    sendbtn.setForeground(Color.WHITE);
    sendbtn.setFont(new Font("Serif", Font.PLAIN, 20));
    sendbtn.addActionListener(new ButtonListener());

    this.add(messageBox);
    this.add(sendbtn); 
  }
  class MessageListener implements MouseListener
  {
    public void mouseClicked(MouseEvent e)
    {
      if (messageBox.getText().equals("Type Your Message...")) {
        messageBox.setText("");
      }
    }
    public void mouseExited(MouseEvent e) 
    {
      if (messageBox.getText().replaceAll("\\s", "").equals("")) 
      {
        messageBox.setText("Type Your Message...");
      }
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    //Boş methodlar
  }
  class MessageKeyListener implements KeyListener
  {
    public void keyPressed(KeyEvent e) 
    {
      if (messageBox.getText().equals("Type Your Message...") || messageBox.getText().equals("Type Your Message...\n")) 
      {
        messageBox.setText("");
      }
      if(e.getKeyCode() == KeyEvent.VK_ENTER)
      {
        if (messageBox.getText().equals("Type Your Message...") || messageBox.getText().equals("Type Your Message...\n")) 
        { 
        } 
        else 
        {
          String message = messageBox.getText();
          String owner = user.getUsername();
          SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
          Date now = new Date();
          String time = dateFormat.format(now);
          Message message1 = new Message(owner,message,time);
          chatRoom.addMessage(message1);
          messageBox.setText("Type Your Message...");
        }   
      }
      
      
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    //Boş methodlar
  }
  class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    { 
      if (messageBox.getText().equals("Type Your Message...")) {
        
      } else {
        String message = messageBox.getText();
        String owner = user.getUsername();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date now = new Date();

        String time = dateFormat.format(now);
        Message message1 = new Message(owner,message,time);
        chatRoom.addMessage(message1);
        messageBox.setText("Type Your Message...");
      }
      
    
    }
  }
  
  
  // time Listener
  //Her seferinde arraylisti güncelle
  class MyListener implements ActionListener
  { 
    public void actionPerformed(ActionEvent event)
    {  
      //ChatCenterPanel.chatBox.setText("T");
      try{
      chatRoom.updateMessage();
       chatBox.setText(allMessage(chatRoom.getMessages()));
      }
      catch(NullPointerException e)
      {
         ChatLastPanel.t.stop();
            ChatSouthPanel.timer.stop();
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) messageBox);
            f1.getContentPane().removeAll();
            f1.add(new MainPanel(user), BorderLayout.CENTER);
            f1.add(new UpperBar(user), BorderLayout.NORTH);
            f1.repaint();
            f1.setVisible(true);
            JOptionPane.showMessageDialog(f1, "The Chat Room has been deleted by another user");
      }  

        //ChatCenterPanel.chatBox.append(appendMessage.getOwner() + " "+appendMessage.getMessage()+ " "+ appendMessage.getTime() +"\n"+"\n");
      
    }
  } 
  public String allMessage(ArrayList<Message> messages)
  {
    String result = "";
    for (int i =0; i<messages.size(); i++)
    {
      result = result + messages.get(i).getOwner() +": "+ messages.get(i).getMessage()+"\n ("+messages.get(i).getTime()+")"+"\n"; 
    } 
    return result;
  }
  
  
}
