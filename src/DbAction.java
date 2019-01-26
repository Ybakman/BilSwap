/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
public class DbAction
{
  //Db variables
  public static MongoClientURI connectionString;
  public static MongoClient mongoClient;
  public static MongoDatabase database;
  
   /**
   * This constructor forms a new Database connection to MongoDB
   * @return buttons
   */
  public DbAction()
  {
       // To connect online db, uncomment below this line and comment other connectionString line
      //connectionString = new MongoClientURI("mongodb://Revegantor:Babasonlar23@ds241664.mlab.com:41664/projectdb");
     connectionString = new MongoClientURI("mongodb://localhost:27017");
     mongoClient = new MongoClient(connectionString);
     database = mongoClient.getDatabase("projectdb");
  }
  

}  
  