
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
 * This is the panel that contains main panel and add request button
 *
 * @author Alperen
 */
public class YourRequestsLastPanel extends JPanel {

    static int requetCount = 0;
    final int MAX_REQUEST = 3;
    static JPanel panel;
    JPanel main;
    User user;
    
    /**
     * adds main panel and creates add request button
     * @param user 
     */
    public YourRequestsLastPanel(User user) {
        super();
        //setting layout
        this.setLayout(new BorderLayout());
        //adding title
        JPanel title = new JPanel();
        this.user = user;
        user.updateUser();
        title.setPreferredSize(new Dimension(200, 100));
        JLabel label = new JLabel("Your Requests");
        label.setForeground(new Color(183,15,10));
        label.setFont(new Font("Serif", Font.PLAIN, 40));
        title.add(label);
        this.add(title, BorderLayout.NORTH);
        //addindg mainpanel
        main = new YourRequestsMainPanel();
        this.add(main, BorderLayout.CENTER);
        //adding addbutton and reminder
        JPanel south = new JPanel(new GridLayout(2, 3));
        south.setPreferredSize(new Dimension(200, 100));
        JButton addbtn = new JButton("ADD A SWAP REQUEST");
        addbtn.setFont(new Font("Serif", Font.PLAIN, 20));
        addbtn.setBackground(new Color(20, 100, 246));
        addbtn.setForeground(Color.WHITE);
        //addbtn.setOpaque(true);
        //addbtn.setBorderPainted(false);
        south.add(new JLabel());
        south.add(addbtn);
        south.add(new JLabel());
        south.add(new JLabel());
        JLabel reminder = new JLabel("*You cannot make more than three requests");
        reminder.setForeground(Color.red);
        reminder.setFont(new Font("Serif", Font.PLAIN, 14));
        south.add(reminder);
        ActionListener buttonListener = new ButtonListener();
        addbtn.addActionListener(buttonListener);
        this.add(south, BorderLayout.SOUTH);
        panel = this;
        for (int i = 0; i < user.getRequests().size(); i++) {

            Request request = user.getRequests().get(i);
            String courseOffered = request.getCourseOffered().toString();
            String courseRequested = request.getCourseRequested().toString();
            boolean matchStatus = request.getMatchStatus();
            main.add(new YourRequestsSubPanel(courseOffered, courseRequested, matchStatus, user, request.getChatRoom()));
        }
    }

    public JPanel getItself() {
        return this;
    }
    
     /**
     * directs user to add request page. if user has already ahve 3 request shows a warning
     */
    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (user.getRequests().size() < MAX_REQUEST) {
                JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());

                f1.getContentPane().removeAll();
                f1.add(new UpperBar(user), BorderLayout.NORTH);
                f1.add(new LastPanel(user), BorderLayout.CENTER);
                f1.repaint();
                f1.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "You can't have more than 3 requests");
            }
        }
    }

}
