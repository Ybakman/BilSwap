
/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.MongoDatabase;
public class Message
{
  //Declare variables
  String message;
  String time;
  String owner;
  String dbId;
  private MongoDatabase db;
  private MongoCollection<Document> collection;
  
  /**
   * This contructor creates a new message by given parameters
   * @param owner
   * @param message
   * @param time 
   */
  public Message(String owner, String message, String time)
  {
    db = DbAction.database;
    collection = db.getCollection("Messages");
    
    this.owner = owner;
    this.time = time;
    this.message = message;
    //Update Database
    Document newMessage = new Document("owner", owner)
                     .append("time", this.time)
                     .append("message", this.message);
    
    collection.insertOne(newMessage);
    ObjectId objectId = (ObjectId)newMessage.get("_id");
    dbId = objectId.toString();
  }
  
  /**
   * This contructor creates a new message by database id
   * @param id
   */
  public Message(String id)
  {
    db = DbAction.database;
    collection = db.getCollection("Messages");
    dbId = id;
    //Get message From database
    Document messages = collection.find(eq("_id",new ObjectId(dbId))).first();
    this.owner = messages.get("owner").toString();
    this.message = messages.get("message").toString();
    this.time = messages.get("time").toString();
  }
  
  //Get methods
  
   /**
   * This method gives name of the owner of the message
   * @return owner
   */
  public String getOwner()
  {
    return owner;
  }
  
   /**
   * This method gives the real message
   * @return message
   */
  public String getMessage()
  {
    return message;
  }
  
   /**
   * This method gives the time of the message
   * @return time
   */
  public String getTime()
  {
    return time;
  }
  
   /**
   * This method gives the real message
   * @return message
   */
  public String getId()
  {
    return dbId;
  }  
    
}  
  