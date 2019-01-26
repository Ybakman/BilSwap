/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ScrollPanel extends JPanel {
    
    // variables
    CoursePanel coursePanel;
    JScrollPane scrollPane;
   
 /**
   * This contrustor creates a scrollpanel by given parameters
   * @param header
   */
    public ScrollPanel(String header) {
        setPreferredSize(new Dimension(300, 250));
        coursePanel = new CoursePanel();
        scrollPane = new JScrollPane(coursePanel);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(new JLabel(header), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
 /**
   * This contrustor creates a scrollpanel by given parameters
   * @param header
   * @param department
   */
    public ScrollPanel(String header, String department) {
        setPreferredSize(new Dimension(300, 250));
        coursePanel = new CoursePanel(department);
        scrollPane = new JScrollPane(coursePanel);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(new JLabel(header), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
 /**
   * This contrustor creates a scrollpanel by given parameters
   * @param header
   * @param department
   * @param courseCode
   */
    public ScrollPanel(String header, String department, int courseCode) {
        setPreferredSize(new Dimension(300, 250));
        coursePanel = new CoursePanel(department, courseCode);
        scrollPane = new JScrollPane(coursePanel);
        scrollPane.setPreferredSize(new Dimension(250, 150));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(new JLabel(header), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
  /**
   * This method gives coursePanel
   * @return coursePanel
   */
    public CoursePanel getCoursePanel() {
        return coursePanel;
    }
 /**
   * This method gives coursePanel' buttons
   * @return coursePanel.getButtons();
   */
    public ArrayList<JButton> getButtons() {
        return coursePanel.getButtons();
    }
}
