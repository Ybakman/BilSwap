/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainPanel extends JPanel
{
  JButton make;
  JButton your;
  User user;
   /**
   * This constructor creates main panel of the program by given user
   * @param user
   */
  public MainPanel(User user)
  {
    this.user = user;
    JPanel buttonPanel = new ButtonPanel("Make a Swap Request");
    JPanel buttonPanel1 = new ButtonPanel("Your Swap Requests");
    JPanel buttonPanel2 = new ButtonPanel("Profile Settings");
    JPanel buttonPanel3 = new ButtonPanel("What is BilSwap");
    setLayout(new GridLayout(6,1));
    make = new JButton("Make a Swap Request");
    your = new JButton("Your Requests");
    add(new JPanel());
    add(buttonPanel);
    add(buttonPanel1);
    add(buttonPanel2);
    add(buttonPanel3);
    add(new JPanel());
    ((ButtonPanel)buttonPanel).getButton().addActionListener(new MakeListener());
    ((ButtonPanel)buttonPanel1).getButton().addActionListener(new yourListener());
    ((ButtonPanel)buttonPanel2).getButton().addActionListener(new SettingsListener());
    ((ButtonPanel)buttonPanel3).getButton().addActionListener(new InfoListener());
  }
  
  /**
   * Directs make a swap request page
   */
  class MakeListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component)event.getSource());
      f1.getContentPane().removeAll();
      f1.add(new UpperBar(user), BorderLayout.NORTH);
      f1.add(new LastPanel(user), BorderLayout.CENTER);
      f1.repaint();
      f1.setVisible(true);
    }
  }
  /**
   * Directs make a swap request page
   */
  class yourListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component)event.getSource());
      f1.getContentPane().removeAll();
      f1.add(new UpperBar(user), BorderLayout.NORTH);
      f1.add(new YourRequestsLastPanel(user),BorderLayout.CENTER);
      f1.repaint();
      f1.setVisible(true);
    }
  }
  
  /**
   * Directs settings page
   */
  class SettingsListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component)event.getSource());
      f1.getContentPane().removeAll();
      f1.add(new UpperBar(user), BorderLayout.NORTH);
      f1.add(new Settings(user),BorderLayout.CENTER);
      f1.repaint();
      f1.setVisible(true);
    }
  }
  class InfoListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component)event.getSource());
      f1.getContentPane().removeAll();
      f1.add(new UpperBar(user), BorderLayout.NORTH);
      f1.add(new InformationPage(),BorderLayout.CENTER);
      f1.repaint();
      f1.setVisible(true);
    }
  }  
  
}  







