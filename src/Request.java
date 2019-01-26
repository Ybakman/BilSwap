
/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import com.mongodb.client.MongoDatabase;

public class Request
{
  //Declare variables
  private Course courseOffered;
  private Course courseRequested;
  private ChatRoom chatRoom;
  private Boolean matchStatus;
  private User owner;
  private MongoDatabase db;
  private MongoCollection<Document> collection;
  private String dbId;
  private RequestCycle cycle;
  String strOwner;
  private ArrayList<Request> relation; 
  
   /**
   * This constructor creates a request by given id and user
   * @param id
   * @param owner
   */
  Request(String id, User owner)
  {
    //get from database
    db = DbAction.database;
    collection = db.getCollection("Requests");
    dbId = id;
    strOwner = owner.getUsername();
    ObjectId objectId = new ObjectId(id);
    Document request = collection.find(eq("_id",objectId)).first();
    //Initialize variables
    courseOffered = new Course(request.get("courseOffered").toString());
    courseRequested = new Course(request.get("courseRequested").toString());
    this.owner = owner;
    matchStatus = (Boolean)request.get("matchStatus");
    if(matchStatus)
    {
      chatRoom = new ChatRoom(request.get("chatRoom").toString());
    }
  }
   /**
   * This constructor creates a request by given id 
   * @param id
   */
  Request(String id)
  {
    //get from database
    db =  DbAction.database;
    collection = db.getCollection("Requests");
    dbId = id;
    //System.out.println(id);
    ObjectId objectId = new ObjectId(id);
    Document request = collection.find(eq("_id",objectId)).first();
    //Initialize variables
  
    
    courseOffered = new Course(request.get("courseOffered").toString());
    courseRequested = new Course(request.get("courseRequested").toString());
    owner = new User(request.get("owner").toString());
    strOwner = owner.getUsername();
    matchStatus = (Boolean)request.get("matchStatus");
    chatRoom = null;
    if(matchStatus)
    {
      chatRoom = new ChatRoom(request.get("chatRoom").toString());
    }
  }
  
   /**
   * This constructor creates a request by given id for only chatRooms
   * @param id
   * @param check
   */
    Request(String id,boolean check)
  {
    //get from database
    db = DbAction.database;
    collection = db.getCollection("Requests");
    dbId = id;
    ObjectId objectId = new ObjectId(id);
    Document request = collection.find(eq("_id",objectId)).first();
    //Initialize variables
 
    courseOffered = new Course(request.get("courseOffered").toString());
    courseRequested = new Course(request.get("courseRequested").toString());
    MongoCollection<Document> findUser = db.getCollection("Users");
    String ownerId = request.get("owner").toString();
    Document user = findUser.find(eq("_id",new ObjectId(ownerId))).first();
    strOwner = user.get("username").toString();
    matchStatus = (Boolean)request.get("matchStatus");
  }
  
     /**
   * This constructor creates a request by given parameters
   * @param courseOffered
   * @param courseRequested
   * @param owner
   */
  Request(Course courseOffered, Course courseRequested, User owner)
  {
    db = DbAction.database;
    collection = db.getCollection("Requests");
    //Create new one
    this.relation = new ArrayList<Request>();
    this.courseOffered = courseOffered;
    this.courseRequested = courseRequested;
    this.owner = owner;
    matchStatus = false;
    chatRoom = null;
    String[] relationDb = new String[0]; // başta 0 sonra zaten methodla güncelleniyor
    //Push to DB
    Document newRequest = new Document("courseOffered", courseOffered.getId())
      .append("courseRequested", courseRequested.getId())
      .append("owner", owner.getId())
      .append("matchStatus", false)
      .append("chatRoom", "")
      .append("relation", Arrays.asList(relationDb));
    
    collection.insertOne(newRequest);
    ObjectId id = (ObjectId)newRequest.get( "_id" );
    dbId = id.toString();
    cycle = new RequestCycle();
    for (Request requestI : cycle.getAllRequests()) {
      if(this.getCourseRequested().equals(requestI.getCourseOffered()))
      {
        getRelation();
        addRelation(requestI);
      }  
      if(this.getCourseOffered().equals(requestI.getCourseRequested()))
      {
        requestI.getRelation();
        requestI.addRelation(this);
      }  
    }
    
  }
  
  
   /**
   * This method adds relation to the request and update db
   * @param r
   */
  public void addRelation(Request r)
  {
    relation.add(r);
    //Create array for db
    String[] relationDb = new String[relation.size()];
    for(int i =0; i< relation.size(); i++)
    {
      relationDb[i] = relation.get(i).getId();
    }
    
    //Push to Db
    collection.updateOne(eq("_id",  new ObjectId(dbId)), new Document("$set", new Document("relation", Arrays.asList(relationDb))));
  }
  
  /**
   * This method adds remove the chatroom of request and update db
   */
  public void removeChatRoom()
  {
    if(chatRoom != null)
    {
      chatRoom =null;
    }
    collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("chatRoom", "")));
  }
  
    /**
   * This method make all relation of the request
   */
  public void makeRelation()
  {
    relation = new ArrayList<Request>(); 
    cycle = new RequestCycle();
    for (Request requestI : cycle.getAllRequests()) {
      if(this.getCourseRequested().equals(requestI.getCourseOffered()))
      {
        getRelation();
        addRelation(requestI);
      }  
      if(this.getCourseOffered().equals(requestI.getCourseRequested()))
      {
        requestI.getRelation();
        requestI.addRelation(this);
      }  
    }
  }
  
 /**
   * This method deletes the relation of the request by given id
   * @String id
   */
  public void deleteRelation(String id)
  {
      ArrayList<Request> relation1 = getRelation();
      System.out.println(relation1.size());
      for(int i = 0; i<relation1.size(); i++)
      {
    	  System.out.println(relation1.get(i).getId()+ "BABA" + i);
    	  if(relation1.get(i).getId().equals(id))
    	  {
    		  relation1.remove(i);
    		  System.out.println("Deleted" + i);
    		  i--;
    	  }  
      }
      relation = relation1;
      String[] relationDb = new String[relation1.size()];
      for(int i = 0; i<relationDb.length; i++)
      {
    	  relationDb[i] = relation1.get(i).getId();
      }
      collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("relation", Arrays.asList(relationDb))));
      /*Boolean exist = false;
      for(int i = 0 ; i<relation1.size(); i++)
      {
    	 if(relation1.get(i).getId().equals(id))
    	 {
    		 exist = true;
    	 }
      }
      if(exist)
      { 
      String[] relationDb;
      relationDb = new String[0];
      if(relation1.size() >0 && exist)
      { 
      relationDb = new String[relation1.size()-1];
      if(relation1.size() >1 && exist)
      {  
      int count = 0;
      for(int i = 0; i<relation1.size(); i++)
      {
        if(relation1.get(i).getId().equals(id))
        {
          count--;
        }
        else
        {
          relationDb[count] = relation1.get(i).getId();
        }
        count++;
      }
      }
      } 
      collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("relation", Arrays.asList(relationDb))));
      }*/
  }      
    
  //Set Methods
  
 /**
   * This method sets the offered course and update db
   * @param courseOffered
   */
  public void setCourseOffered(Course courseOffered)
  {
    this.courseOffered = courseOffered;
    //Update Database
    collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("courseOffered", courseOffered.getId())));
  }
  
  /**
   * This method sets the reqeusted course and update db
   * @param courseRequested
   */
  public void setCourseRequested(Course courseRequested)
  {
    this.courseRequested = courseRequested;
    // Update Database
    collection.updateOne(eq("_id",new  ObjectId(dbId)), new Document("$set", new Document("courseRequested", courseRequested.getId())));
  }
  
  /**
   * This method sets the chatRoom and update db
   * @param chatRoom
   */
  public void setChatRoom(ChatRoom chatRoom)
  {
    this.chatRoom = chatRoom;
    // Update Database maybe take String a paramater
    collection.updateOne(eq("_id",new ObjectId(dbId)), new Document("$set", new Document("chatRoom", chatRoom.getId())));
  }
  
  /**
   * This method sets the match status and update db
   * @param matchStatus
   */
  public void setMatchStatus(boolean matchStatus)
  {
    this.matchStatus = matchStatus;
    // Update Database
    collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("matchStatus", matchStatus)));
  }
  /**
   * This method sets the owner and update db
   * @param owner
   */
  public void setOwner(User owner)
  {
    this.owner = owner;
    // Update Database
    collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("owner", owner.getId())));
  }
  
  //Get Methods
  
  /**
   * This method gives to relations of the request
   * @return relation
   */
  public ArrayList<Request> getRelation()
  {
    if(relation == null)
    {
      ObjectId objectId = new ObjectId(dbId);
      Document request = collection.find(eq("_id",objectId)).first();
      relation = new ArrayList<Request>();
      for(int i = 0; i < ((ArrayList)request.get("relation")).size(); i++)
      {
    	  if(((String)((ArrayList)request.get("relation")).get(i)) != "" && ((String)((ArrayList)request.get("relation")).get(i)) != null)
    	  {
        Request req = new Request(((String)((ArrayList)request.get("relation")).get(i)));
        relation.add(req);
    	  }
      }
    } 
    return relation;
  }
  
  /**
   * This method gives to offered course of the request
   * @return courseOfferred
   */
  public Course getCourseOffered()
  {
    return courseOffered;
  }
  
  /**
   * This method gives to database id of the request
   * @return dbId
   */
  public String getId()
  {
    return dbId;
  }  
  
  /**
   * This method gives to requested course of the request
   * @return courseRequested
   */
  public Course getCourseRequested()
  {
    return courseRequested;
  }
  
  /**
   * This method gives to chatroom of the request
   * @return chatRoom
   */
  public ChatRoom getChatRoom()
  {
    return chatRoom;
  }
  
  /**
   * This method gives to matchStatus of the request
   * @return matchStatus
   */
  public boolean getMatchStatus()
  {
    return matchStatus;
  }
  
  /**
   * This method gives to owner of the request
   * @return owner
   */
  public User getOwner()
  {
    return owner;
  }
  /**
   * This method gives to username of the owner  of the request
   * @return strOwner
   */
  public String getStrOwner()
  {
    return strOwner;
  }
}   



    
  