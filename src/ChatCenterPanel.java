
/**
 *@author: Alperen Ozis
 * @version: 24/12/2018
 */ 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;


/**
 * This is the panel where chatBox is displayed
 * @author Alperen
 */
public class ChatCenterPanel extends JPanel {

    public JTextArea chatBox;

    /**
     * Creates a new JTextArea and adds it to the panel
     */   
    public ChatCenterPanel() {
        this.setLayout(new BorderLayout());
        chatBox = new JTextArea();
        chatBox.setFont(new Font("Serif", Font.PLAIN, 23));
        chatBox.setEditable(false);
        chatBox.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(chatBox, BorderLayout.CENTER);
    }

  
}