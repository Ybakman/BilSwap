/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;
import javax.swing.*;
import java.awt.*;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;

public class CoursePanel extends JPanel
{
  //Declare variables
  ArrayList<JButton> buttons;
  JScrollPane scrollPane;
  MongoDatabase db;
  
   /**
   * This constructor creates a course panel
   */
  public CoursePanel()
  {
    setPreferredSize(new Dimension(200,250));
    setLayout(new GridLayout(4,2));
    buttons = new ArrayList<JButton>();
    db = DbAction.database;
    MongoCollection<Document> collection = db.getCollection("Courses");
    MongoCursor<Document> cursor = collection.find().iterator();
    try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();
        String course = mydoc.get("Department").toString();
        JButton newButton = new CourseButton(course);
        if(checkRepetation(newButton))
        {
          buttons.add(newButton);
        }  
      }
    } finally {
      cursor.close();
    }
    
    for(int i = 0; i<buttons.size(); i++)
    {
      add(buttons.get(i));
    }
  }
  
  /**
   * This constructor creates a course panel with given department
   * @param department
   */
  public CoursePanel(String department)
  {
    setBackground(Color.white);
    setPreferredSize(new Dimension(200,250));
    setLayout(new GridLayout(4,2));
    buttons = new ArrayList<JButton>();
    db = DbAction.database;
    MongoCollection<Document> collection = db.getCollection("Courses");
    MongoCursor<Document> cursor = collection.find(eq("Department", department)).iterator();
    try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();
        int course = Integer.parseInt((mydoc.get("courseCode").toString()));
        JButton newButton = new CourseButton(course+"");
        if(checkRepetation(newButton))
        {
          buttons.add(newButton);
        }  
      }
    } finally {
      cursor.close();
    }
    for(int i = 0; i<buttons.size(); i++)
    {
      add(buttons.get(i));
    }
    
  }
  
  /**
   * This constructor creates a course panel with given department and courseCode
   * @param department
   * @param courseCode
   */
  public CoursePanel (String department, int courseCode)
  {
    setPreferredSize(new Dimension(200,250));
    setLayout(new GridLayout(4,2));
    buttons = new ArrayList<JButton>();
    db = DbAction.database;
    MongoCollection<Document> collection = db.getCollection("Courses");
    MongoCursor<Document> cursor = collection.find(and(eq("Department", department),eq("courseCode", courseCode))).iterator();
    try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();
        int section = Integer.parseInt((mydoc.get("section").toString()));
        JButton newButton = new CourseButton(section+"");
        if(checkRepetation(newButton))
        {
          buttons.add(newButton);
        }  
      }
    } finally {
      cursor.close();
    }
    for(int i = 0; i<buttons.size(); i++)
    {
      add(buttons.get(i));
    }
  } 
  /**
   * This method gives panel's buttons
   * @return buttons
   */
  public ArrayList<JButton> getButtons()
  {
    return buttons;
  }
  
   /**
   * This method checks whether there is more than one button
   * @param button
   * @return boolean
   */
  public boolean checkRepetation(JButton button)
  {
    for(int i = 0; i<buttons.size(); i++)
    {
      if(button.getText().equals(buttons.get(i).getText()))
      {
        return false;
      }  
    }
    return true;
  }   
  
}  








