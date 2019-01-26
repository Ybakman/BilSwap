/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * This is the panel that contains subpanels which requests are displayed in
 * @author Alperen
 */

public class YourRequestsMainPanel extends JPanel {

    public YourRequestsMainPanel() {
        // setting layout 
        this.setLayout(new GridLayout(5, 0));
        // addin headers
        JPanel headers = new JPanel(new GridLayout(0, 5));
        //header1
        JPanel header1 = new JPanel(new BorderLayout());
        header1.setBackground(Color.LIGHT_GRAY);
        JLabel label1 = new JLabel("Course Offered");
        label1.setFont(new Font("Serif", Font.PLAIN, 20));
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        header1.setSize(new Dimension(300, 100));
        header1.setBorder(BorderFactory.createLineBorder(Color.black));
        header1.add(label1, BorderLayout.CENTER);
        //header2
        JPanel header2 = new JPanel(new BorderLayout());
        header2.setBackground(Color.LIGHT_GRAY);
        JLabel label2 = new JLabel("Course Requested");
        label2.setFont(new Font("Serif", Font.PLAIN, 20));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        header2.setSize(new Dimension(300, 100));
        header2.setBorder(BorderFactory.createLineBorder(Color.black));
        header2.add(label2, BorderLayout.CENTER);
        //header3
        JPanel header3 = new JPanel(new BorderLayout());
        header3.setBackground(Color.LIGHT_GRAY);
        JLabel label3 = new JLabel("Match Status");
        label3.setFont(new Font("Serif", Font.PLAIN, 20));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        header3.setSize(new Dimension(300, 100));
        header3.setBorder(BorderFactory.createLineBorder(Color.black));
        header3.add(label3, BorderLayout.CENTER);

        headers.add(header1);
        headers.add(header2);
        headers.add(header3);

        this.add(headers);

    }

}
