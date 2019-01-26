
/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 */ 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * this is the panel that contains 1 request in 1 row
 *
 * @author Alperen
 */
public class YourRequestsSubPanel extends JPanel {

    User user;
    String courseOffered;
    String courseRequested;
    Boolean matchStatus;
    ChatRoom chat;
     /**
     * it creates a row that contains a request, chatroom button and cancel button
     * @param courseOffered
     * @param courseRequested
     * @param matchStatus
     * @param user
     * @param chat 
     */
    public YourRequestsSubPanel(String courseOffered, String courseRequested, boolean matchStatus, User user, ChatRoom chat) {
        //setting layout
        this.setLayout(new GridLayout(0, 5));
        this.user = user;
        this.courseOffered = courseOffered;
        this.courseRequested = courseRequested;
        this.chat = chat;
        //courseoffered
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label1 = new JLabel(courseOffered);
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        panel1.add(label1);
        panel1.setSize(new Dimension(200, 50));
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(panel1, BorderLayout.CENTER);
        //courserequested
        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label2 = new JLabel(courseRequested);
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        panel2.add(label2);
        panel2.setSize(new Dimension(200, 50));
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(panel2, BorderLayout.CENTER);
        //matchstatus
        this.matchStatus = matchStatus;
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setSize(new Dimension(200, 50));
        panel3.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label3 = new JLabel();
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        if (matchStatus == true) {

            label3.setText("Matched");
        } else {
            label3.setText("UnMatched");
        }
        panel3.add(label3);
        this.add(panel3, BorderLayout.CENTER);
        //chatroom button
        JPanel panel4 = new JPanel();
        JButton chatRoom = new JButton("Chat Room");
        chatRoom.setFont(new Font("Serif", Font.PLAIN, 20));
        chatRoom.setPreferredSize(new Dimension(150, 50));
        chatRoom.setForeground(Color.WHITE);
        if (matchStatus == true) {
            chatRoom.setBackground(new Color(20, 100, 246));
            //chatRoom.setOpaque(true);
            //chatRoom.setBorderPainted(false);
        } else {
            chatRoom.setBackground(Color.LIGHT_GRAY);
            //chatRoom.setOpaque(true);
            //chatRoom.setBorderPainted(false);
        }
        ActionListener chatListener = new ChatListener();
        chatRoom.addActionListener(chatListener);

        panel4.add(chatRoom);
        this.add(panel4);
        //cancel button
        JPanel panel5 = new JPanel();
        JButton deletebtn = new JButton("Cancel");
        deletebtn.setPreferredSize(new Dimension(100, 50));
        deletebtn.setBackground(new Color(183, 15, 10));
        deletebtn.setForeground(Color.WHITE);
        //deletebtn.setOpaque(true);
        //deletebtn.setBorderPainted(false);
        deletebtn.setFont(new Font("Serif", Font.PLAIN, 17));
        ActionListener buttonListener = new ButtonListener();
        deletebtn.addActionListener(buttonListener);
        panel5.add(deletebtn);
        this.add(panel5);

    }

    public JPanel getItself() {
        return this;
    }
    
    /**
     * cancels the request
     */
    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < user.getRequests().size(); i++) {

                if (user.getRequests().get(i).getCourseOffered().toString().equals(courseOffered)
                        && user.getRequests().get(i).getCourseRequested().toString().equals(courseRequested)) {
                    Request req = user.getRequests().get(i);
                    user.removeRequest(req);
                    RequestCycle cycle = new RequestCycle();
                    cycle.removeRequest(req);

                }
            }
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
            f1.getContentPane().removeAll();
            f1.add(new UpperBar(user), BorderLayout.NORTH);
            f1.add(new YourRequestsLastPanel(user), BorderLayout.CENTER);
            f1.repaint();
            f1.setVisible(true);
        }
    }
    
    /**
     * Directs user to chatroom page if there is one
     */
    class ChatListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
            if (matchStatus) {
            	try {
                
                f1.getContentPane().removeAll();
                chat.changeAvailability(user.getUsername());
                f1.add(new ChatUpperBar(user, chat), BorderLayout.NORTH);
                f1.add(new ChatLastPanel(chat, user, f1), BorderLayout.CENTER);
                f1.repaint();
                f1.setVisible(true);
                
            	}
            	catch(NullPointerException e) {
            		
            		 //JFrame f2 = (JFrame) SwingUtilities.windowForComponent((Component) getItself());
            		 JOptionPane.showMessageDialog(f1, "The chatroom has been removed");
                     f1.getContentPane().removeAll();
                     f1.add(new UpperBar(user), BorderLayout.NORTH);
                     f1.add(new YourRequestsLastPanel( user), BorderLayout.CENTER);
                     f1.repaint();
                     f1.setVisible(true);
            	}
            }
        }

    }

}
