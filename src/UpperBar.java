
/**
 * this is the panel that contains 3 button. one directs to main page, one
 * direts to profile page and one logouts
 *
 * @author Alperen
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class UpperBar extends JPanel {

    User user;
    ImageIcon logo;
    ImageIcon logout;
    ImageIcon userIcon;
    
    /**
     * creates and adds buttons to the panel
     * @param user
     */
    public UpperBar(User user) {
        logo = new ImageIcon(getClass().getResource("logo.png"));
        logout = new ImageIcon(getClass().getResource("exit.png"));
        userIcon = new ImageIcon(getClass().getResource("user.png"));

        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.setBackground(new Color(161, 169, 196));
        this.user = user;
        this.setPreferredSize(new Dimension(800, 110));
        JButton userB = new JButton(user.getUsername());
        JButton toMainB = new JButton(logo);
        JButton logoutB = new JButton("Logout");

        JLabel userL = new JLabel(userIcon);

        JPanel userP = new JPanel();
        JLabel logoutL = new JLabel(logout);

        userP.setPreferredSize(new Dimension(250, 100));
        userP.setBackground(new Color(161, 169, 196));
        userB.setPreferredSize(new Dimension(155, 100));
        userB.setFont(new Font("Serif", Font.BOLD, 25));
        userB.setHorizontalAlignment(JLabel.LEFT);
        userB.setFocusPainted(false);
        userB.setMargin(new Insets(0, 0, 0, 0));
        userB.setContentAreaFilled(false);
        userB.setBorderPainted(false);
        userB.setOpaque(false);

        userB.addActionListener(new UserNameListener());
        userP.add(userB);
        userP.add(userL);

        JPanel toMainP = new JPanel();
        toMainP.add(toMainB);
        toMainP.setBackground(new Color(161, 169, 196));
        toMainP.setPreferredSize(new Dimension(250, 100));
        toMainB.setPreferredSize(new Dimension(200, 100));
        toMainB.setHorizontalAlignment(JLabel.CENTER);
        toMainB.setFont(new Font("Serif", Font.BOLD, 35));
        toMainB.setFocusPainted(false);
        toMainB.setMargin(new Insets(0, 0, 0, 0));
        ActionListener toMainListener = new toMainListener();
        toMainB.addActionListener(toMainListener);

        toMainB.setContentAreaFilled(false);
        toMainB.setBorderPainted(false);
        toMainB.setOpaque(false);

        JPanel logoutP = new JPanel();

        logoutP.setBackground(new Color(161, 169, 196));
        logoutB.setPreferredSize(new Dimension(150, 100));
        logoutP.setPreferredSize(new Dimension(250, 100));
        logoutB.setFont(new Font("Serif", Font.BOLD, 25));
        logoutB.setFocusPainted(false);
        logoutB.setMargin(new Insets(0, 0, 0, 0));
        logoutB.setHorizontalAlignment(JLabel.RIGHT);
        logoutB.setContentAreaFilled(false);
        logoutB.setBorderPainted(false);
        logoutB.setOpaque(false);
        ActionListener logoutListener = new logoutListener();
        logoutB.addActionListener(logoutListener);
        logoutP.add(logoutB);
        logoutP.add(logoutL);

        this.add(userP);
        this.add(toMainP);
        this.add(logoutP);
    }

    /**
     * direct to main page
     */
    class toMainListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
            f1.getContentPane().removeAll();
            f1.setLayout(new BorderLayout());
            f1.add(new MainPanel(user), BorderLayout.CENTER);
            f1.add(new UpperBar(user), BorderLayout.NORTH);
            f1.repaint();
            f1.setVisible(true);
        }
    }
    
   /**
     * directs to profile page
     */
    class UserNameListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
            f1.getContentPane().removeAll();
            f1.add(new Settings(user), BorderLayout.CENTER);
            f1.add(new UpperBar(user), BorderLayout.NORTH);
            f1.repaint();
            f1.setVisible(true);
        }
    }
     
    /**
     * logouts
     */
    class logoutListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
            f1.getContentPane().removeAll();
            f1.dispose();
            JFrame frame = new LoginForm();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

        }
    }

}
