/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 */ 
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 *
 * @author Alperen
 */
public class ChatLastPanel extends JPanel {

    ChatRoom chatRoom;
    JPanel west;
    User user;
    JFrame f1;
    public static Timer t;

    public ChatLastPanel(ChatRoom chatRoom, User user, JFrame f1) {
        this.chatRoom = chatRoom;
        this.user = user;
        this.setLayout(new BorderLayout());
         //adding west panel
        west = new ChatWestPanel(chatRoom);
        this.add(west, BorderLayout.WEST);
         //adding center panel
        JPanel center = new ChatCenterPanel();
        JScrollPane scrollPane = new JScrollPane(center);
        this.add(scrollPane, BorderLayout.CENTER);
        this.f1 = f1;
         //adding south panel
        JPanel south = new ChatSouthPanel(chatRoom, user, ((ChatCenterPanel) center).chatBox);
        this.add(south, BorderLayout.SOUTH);
        f1.addWindowListener((WindowListener) (new CloseListener()));

        MyListener listener = new MyListener();
        t = new Timer(300, listener);
        t.start();
    }

    public JPanel getItself() {
        return this;
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
          try{
            getItself().remove(west);
            chatRoom.updateMessage();

            west = new ChatWestPanel(chatRoom);
            getItself().add(west, BorderLayout.WEST);
            getItself().revalidate();
            getItself().repaint();
          }
          catch(NullPointerException e)
          {
            ChatLastPanel.t.stop();
            ChatSouthPanel.timer.stop();
          }    
        }

    }

    class CloseListener implements WindowListener {

        public void windowActivated(WindowEvent e) {
        }

        public void windowClosed(WindowEvent e) {
            chatRoom.setAvailableFalse(user.getUsername());
            e.getWindow().dispose();
        }

        public void windowClosing(WindowEvent e) {
            chatRoom.setAvailableFalse(user.getUsername());
            e.getWindow().dispose();
        }

        public void windowDeactivated(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowOpened(WindowEvent e) {
        }
    }

}
