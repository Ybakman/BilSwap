/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class RequestPanel extends JPanel {
     
    // declare variables
    ScrollPanel coursePanel;
    JPanel[][] panelList;
    SubmitPanel submitPanel;
    String department;
    int courseCode;
    Request request;
    User user;
    JButton submitButton;
    int section;
    String department1;
    int courseCode1;
    int section1;
    Course course;
    Course course1;
    RequestCycle cycle;
    
    /**
   * This constructor creates request panel by given user
   * @param user
   */
    public RequestPanel(User user) {
        this.user = user;
        department = "";
        courseCode = 0;
        section = 0;
        department1 = "";
        courseCode1 = 0;
        section1 = 0;
        setPreferredSize(new Dimension(800, 600));
        submitPanel = new SubmitPanel();
        submitButton = submitPanel.getButton();
        submitButton.addActionListener(new SubmitListener());
        coursePanel = new ScrollPanel("Choose The Department");
        ScrollPanel coursePanel1 = new ScrollPanel("Choose The Department");
        setLayout(new GridLayout(3, 2));
        panelList = new JPanel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                panelList[i][k] = new JPanel();
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                add(panelList[i][k]);
            }
        }
        panelList[0][0].add(coursePanel);
        panelList[0][2].add(coursePanel1);
        panelList[1][1].add(submitPanel);
        for (int i = 0; i < coursePanel.getCoursePanel().getButtons().size(); i++) {
            coursePanel.getCoursePanel().getButtons().get(i).addActionListener(new ClickListener());
            coursePanel1.getCoursePanel().getButtons().get(i).addActionListener(new ClickListener());
        }
    }

    public RequestPanel getItself() {
        return this;
    }
    
    /**
   * This listener creates a new coursepanel by looking button source
   */
    class ClickListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            // Show New Request Panel
            JButton submitButton1 = (JButton) event.getSource();
            submitButton1.setBorderPainted(false);
            submitButton1.setBackground(new Color(20, 100, 246));
            submitButton1.setOpaque(true);
            submitButton1.setForeground(Color.white);
            for (int i = 0; i < 3; i++) {
                for (int k = 0; k < 3; k++) {
                    if (panelList[i][k].getComponents().length > 0) {
                        if (!(i == 1 && k == 1)) {
                            for (int l = 0; l < ((ScrollPanel) panelList[i][k].getComponents()[0]).getButtons().size(); l++) {
                                if (event.getSource() == ((ScrollPanel) panelList[i][k].getComponents()[0]).getButtons().get(l)) {
                                    //Change Color
                                    for (int z = 0; z < ((ScrollPanel) panelList[i][k].getComponents()[0]).getButtons().size(); z++) {

                                        if ((event.getSource() != ((ScrollPanel) panelList[i][k].getComponents()[0]).getButtons().get(z))) {
                                            JButton otherButton = ((ScrollPanel) panelList[i][k].getComponents()[0]).getButtons().get(z);
                                            otherButton.setBorderPainted(true);
                                            otherButton.setBackground(UIManager.getColor("Panel.background"));
                                            otherButton.setOpaque(true);
                                            otherButton.setForeground(Color.black);
                                        }
                                    }
                                    ScrollPanel newPanel;
                                    if ((i == 0 && k == 0) || (i == 0 && k == 2)) {
                                        if (i == 0 && k == 0) {
                                            department = ((JButton) event.getSource()).getText();
                                            newPanel = new ScrollPanel("Choose The Course", department);
                                        } else {
                                            department1 = ((JButton) event.getSource()).getText();
                                            newPanel = new ScrollPanel("Choose The Course", department1);
                                        }
                                        //Set course's department
                                        panelList[i + 1][k].removeAll();
                                        panelList[i + 1][k].revalidate();
                                        panelList[i + 1][k].repaint();
                                        panelList[i + 1][k].add(newPanel);
                                        for (int m = 0; m < newPanel.getCoursePanel().getButtons().size(); m++) {
                                            newPanel.getCoursePanel().getButtons().get(m).addActionListener(new ClickListener());
                                        }
                                    }
                                    if ((i == 1 && k == 0) || (i == 1 && k == 2)) {
                                        if ((i == 1 && k == 0)) {
                                            courseCode = Integer.parseInt(((JButton) event.getSource()).getText());
                                            newPanel = new ScrollPanel("Choose The Section", department, courseCode);
                                        } else {
                                            courseCode1 = Integer.parseInt(((JButton) event.getSource()).getText());
                                            newPanel = new ScrollPanel("Choose The Section", department1, courseCode1);

                                        }

                                        panelList[i + 1][k].removeAll();
                                        panelList[i + 1][k].revalidate();
                                        panelList[i + 1][k].repaint();
                                        panelList[i + 1][k].add(newPanel);
                                        for (int m = 0; m < newPanel.getCoursePanel().getButtons().size(); m++) {
                                            newPanel.getCoursePanel().getButtons().get(m).addActionListener(new ClickListener());
                                        }
                                    }
                                    if ((i == 2 && k == 0) || (i == 2 && k == 2)) {
                                        if (i == 2 && k == 0) {
                                            section = Integer.parseInt(((JButton) event.getSource()).getText());
                                        } else {
                                            section1 = Integer.parseInt(((JButton) event.getSource()).getText());

                                        }

                                    }
                                }
                            }
                        }
                    }
                }

            }
            getItself().revalidate();
        }
    }
    
    /**
   * This listener submits constructed request to cycle and database
   */
    class SubmitListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (department != "" && courseCode != 0 && section != 0 && department1 != "" && courseCode1 != 0 && section1 != 0) {
                course = new Course(department, courseCode, section);
                course1 = new Course(department1, courseCode1, section1);
                Boolean exist = false;
                for (int i = 0; i < user.getRequests().size(); i++) {
                    if (user.getRequests().get(i).getCourseOffered().getId().equals(course.getId()) && user.getRequests().get(i).getCourseRequested().getId().equals(course1.getId())) {
                        exist = true;
                    }
                }
                if (user.getRequests().size() < 3 && !exist) {
                    request = new Request(course, course1, user);
                    cycle = new RequestCycle();
                    boolean check = cycle.matchCycle(request);

                    if (check) {
 
                        ChatRoom chatRoom = new ChatRoom(cycle.getCycle());
                    }
                    user.addRequest(request);
                    JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
                    f1.getContentPane().removeAll();
                    f1.add(new UpperBar(user), BorderLayout.NORTH);
                    f1.add(new MainPanel(user), BorderLayout.CENTER);
                    f1.repaint();
                    f1.setVisible(true);
                } else {
                    JFrame f1 = (JFrame) SwingUtilities.windowForComponent((Component) event.getSource());
                    f1.getContentPane().removeAll();
                    f1.add(new UpperBar(user), BorderLayout.NORTH);
                    f1.add(new YourRequestsLastPanel(user), BorderLayout.CENTER);
                    f1.repaint();
                    f1.setVisible(true);
                }
            }
        }
    }
}
