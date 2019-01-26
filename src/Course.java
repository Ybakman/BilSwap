/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;


public class Course
{
  //Declare variables
  private String department;
  private int courseCode;
  private int section;
  private String dbId;
  private MongoDatabase db;
  private MongoCollection<Document> collection;
  
   /**
   * This constructor creates a new course by given parameters
   * @param department
   * @param courseCode
   * @param section
   */
  public Course(String department, int courseCode, int section)
  {
    db = DbAction.database;
    collection = db.getCollection("Courses");
    // create new Course
    this.department = department;
    this.courseCode = courseCode;
    this.section = section;
    // Set ID from DB
 
    Document theCourse = collection.find(and(eq("Department", department), eq("courseCode", courseCode),eq("section", section))).first();
    ObjectId objectId = (ObjectId)theCourse.get("_id");
    dbId = objectId.toString();
  }
  
   /**
   * This constructor creates a new course by given courseId
   * @param id which is db id
   */
  public Course(String id)
  {
    db = DbAction.database;
    collection = db.getCollection("Courses");
    dbId = id;
    Document theCourse = collection.find(eq("_id", new ObjectId(id))).first();
    this.department = theCourse.get("Department").toString();
    this.courseCode = Integer.parseInt(theCourse.get("courseCode").toString());
    this.section = Integer.parseInt(theCourse.get("section").toString());
  }  
  
   /**
   * This method compares two courses
   * @param otherCourse
   * @return boolean
   */
  public boolean equals(Course otherCourse)
  {
    if(department.equals(otherCourse.getDepartment()) && (section == otherCourse.getSection()) && (courseCode == otherCourse.getCourseCode()))
    {
      return true;
    }
    return false;
  }
  
   /**
   * This method presents course as string
   * @return result
   */
  public String toString()
  {
    String result = "";
    result = result + department+ "-";
    result = result+ courseCode+ "/";
    result = result + section;
    return result;
  }
  
  //Get methods
  
   /**
   * This method gives the name of department
   * @return department
   */
  public String getDepartment()
  {
    return department;
  }
   /**
   * This method gives the course code
   * @return courseCode
   */
  public int getCourseCode()
  {
    return courseCode;
  }
  
   /**
   * This method gives the course section
   * @return section
   */
  public int getSection()
  {
    return section;
  }
  
   /**
   * This method gives the course db id
   * @return dbId
   */
  public String getId()
  {
    return dbId;
  }  
}  
  
  
    
  