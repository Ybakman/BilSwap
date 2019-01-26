
/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
/** 
 * 
 * @author Mustafa Hilmi BARUT 
 * @date 14.12.2018 
 */ 

// 
public class RequestCycle {
  
  // declare variables
  public  ArrayList<Request> allRequests; 
  public  ArrayList<Request> cycle;
  public MongoDatabase db;
  MongoCollection<Document> collection;
  
  /**
   * This constructor collects all requests from database
   */
  public RequestCycle()
  {
    db = DbAction.database;
    collection = db.getCollection("Requests");
    allRequests = new ArrayList<Request>();
    MongoCursor<Document> cursor = collection.find().iterator();
    try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();
        String requestId = mydoc.get("_id").toString();
        String ownerId = mydoc.get("owner").toString();
        allRequests.add(new Request(requestId, new User(ownerId)));
      }
    } finally {
      cursor.close();
    }
  }  
  
  
  /** 
   * Finds if there is a cycle and makes these requests' matchStatus true and 
   * adds these requests to @cycle 
   * @param startRequest starting request 
   * @return true if a cycle is found; false otherwise 
   */ 
  public  boolean matchCycle( Request startRequest ) { 
    cycle = new ArrayList<Request>(); 
    return myMatchCycle(startRequest, startRequest); 
  } 
  
  
  private boolean myMatchCycle( Request startRequest, Request currentRequest) { 
    for(Request relatedRequest: currentRequest.getRelation()) 
    { 
      if(relatedRequest !=null)
      { 
      if(relatedRequest.getMatchStatus()) 
        continue; 
      if(relatedRequest.getId().equals(startRequest.getId())) { 
        currentRequest.setMatchStatus(true); 
        cycle.add(currentRequest); 
        return true; 
      } 
      if(myMatchCycle(startRequest, relatedRequest)) { 
        currentRequest.setMatchStatus(true); 
        cycle.add(currentRequest); 
        return true; 
      } 
    }
    } 
    return false; 
  } 
  
  /**
   * This method add requests to all requests
   * @param e
   */ 
  public void addRequest(Request e) 
  { 
    allRequests.add(e);
  }
  
  /**
   * This method remove request from all requests and starts cycle again
   * @param e
   */
  public void removeRequest(Request e)
  {
    ArrayList<Request> requestList = new ArrayList<Request>();
    if(e.getChatRoom() != null)
    {  
    
      for(int i = 0; i<e.getChatRoom().getRequests().size(); i++)
      {
        if(!(e.getId().equals(e.getChatRoom().getRequests().get(i).getId())))
        {
       
          e.getChatRoom().getRequests().get(i).setMatchStatus(false);
          e.getChatRoom().getRequests().get(i).removeChatRoom();
          requestList.add(e.getChatRoom().getRequests().get(i));
        }  
      }
      //yok et
      MongoCollection<Document> removeChat = db.getCollection("ChatRooms");
      removeChat.deleteOne(eq("_id", new ObjectId(e.getChatRoom().getId())));
      e.setMatchStatus(false);
      e.removeChatRoom();
      db = DbAction.database;
      //Relation olarak yok et
      MongoCollection<Document> removeRequest = db.getCollection("Requests");
       MongoCursor<Document> cursor = removeRequest.find().iterator();
      try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();

        String requestId = mydoc.get("_id").toString();
        String ownerId = mydoc.get("owner").toString();
        Request check = new Request(requestId, new User(ownerId));
        check.deleteRelation(e.getId());
      }
    } finally {
      cursor.close();
    }
     
      removeRequest.deleteOne(eq("_id", new ObjectId(e.getId())));
     
      for(int i = 0; i<requestList.size(); i++)
      {
        requestList.get(i).makeRelation();
        RequestCycle newCycle = new RequestCycle();
        if(newCycle.matchCycle((requestList.get(i))))
        {
          ChatRoom chat = new ChatRoom(newCycle.getCycle());
        }  
      }
    }
    else
    {
      db = DbAction.database;
      //Relation olarak yok et
      MongoCollection<Document> removeRequest = db.getCollection("Requests");
       MongoCursor<Document> cursor = removeRequest.find().iterator();
      try {
      while (cursor.hasNext()) {
        Document mydoc = cursor.next();
        
        String requestId = mydoc.get("_id").toString();
        String ownerId = mydoc.get("owner").toString();
        Request check = new Request(requestId, new User(ownerId));
        check.deleteRelation(e.getId());
      }
    } finally {
      cursor.close();
    }
      removeRequest.deleteOne(eq("_id", new ObjectId(e.getId())));
    }  
  }
  
  /**
   * This method gives all requests
   * @return allRequests
   */
  public ArrayList<Request> getAllRequests()
  {
    return allRequests;
  }
  
  /**
   * This method gives created cycle
   * @return cycle
   */
  public ArrayList<Request> getCycle()
  {
    return cycle;
  }
  
  //Cycle methodunu her çağırdığın yerde chatroom oluşturmayı dene
  
}