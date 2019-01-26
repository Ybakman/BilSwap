/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;

public class ChatRoom
{
  //Declare variables
  ArrayList<Message> messages;
  ArrayList<String> users;
  ArrayList<Boolean> availabilityOfUsers;
  ArrayList<Request> requests;
  private String dbId;
  private MongoDatabase db;
  private MongoCollection<Document> collection;
  
  
  /**
   * This constructor initializes by given requests
   * @param requests which are the given requests
   */ 
  public ChatRoom(ArrayList<Request> requests)
  {
    db = DbAction.database;
    collection = db.getCollection("ChatRooms");
    users = new ArrayList<String>();
    messages =  new ArrayList<Message>();
    availabilityOfUsers = new ArrayList<Boolean>();
    this.requests = requests;
    
    //Create new One add to database
    for(int i = 0; i< requests.size(); i++)
    {
      availabilityOfUsers.add(false);
      if(requests.get(i).getOwner() != null)
      { 
      users.add(requests.get(i).getOwner().getUsername());
      }
      else
      {
        users.add(requests.get(i).getStrOwner());
      }  
    }
    String[] available = new String[requests.size()];
    String[] usersDb = new String[requests.size()];
    String[] messagesDb = new String[0];
    String[] requestsDb = new String[requests.size()];
    
    for(int i = 0; i< requests.size(); i++)
    {
      available[i] = "0";
    }  
    
    for(int i = 0; i<requests.size(); i++)
    {
      usersDb[i] =  users.get(i);
      requestsDb[i] =  requests.get(i).getId();
    }  
    Document newChat = new Document("messages", Arrays.asList(messagesDb))
                           .append("users", Arrays.asList(usersDb))
                           .append("availability", Arrays.asList(available))
                           .append("requests", Arrays.asList(requestsDb));
    
    collection.insertOne(newChat);
    ObjectId objectId = (ObjectId) newChat.get("_id");

    dbId = objectId.toString();
    //Add chatrom to the given requests
    for(int i  = 0; i<requests.size(); i++)
    {
      this.requests.get(i).setChatRoom(this);
    }  
  }
  
   /**
   * This constructor initializes by given id
   * @param id which is the given database chatroom id
   */
  public ChatRoom(String id)
  {
    //getFromDataBase
    db = DbAction.database;
    collection = db.getCollection("ChatRooms");
    dbId = id;
    ObjectId objectId = new ObjectId(id);
    Document chat = collection.find(eq("_id",objectId)).first();
    users = new ArrayList<String>();
    messages =  new ArrayList<Message>();
    availabilityOfUsers = new ArrayList<Boolean>();
    requests = new ArrayList<Request>();
    ArrayList<String> userList = new ArrayList<String>();
    ArrayList<String> messageList = new ArrayList<String>();
    ArrayList<String> requestList = new ArrayList<String>();
    ArrayList<String> availability = new ArrayList<String>();
    userList = (ArrayList)chat.get("users");
    messageList = (ArrayList)chat.get("messages");
    requestList = (ArrayList)chat.get("requests");
    availability = (ArrayList)chat.get("availability");
    for(int i= 0; i<userList.size(); i++)
    {
      users.add(userList.get(i));
      requests.add((new Request(requestList.get(i),true)));
    }
    for(int i= 0; i<messageList.size(); i++)
    {
      messages.add(new Message(messageList.get(i)));
    }
    //Check in the other File
   
    for(int i = 0; i<availability.size(); i++)
    {
      if(availability.get(i).equals("1"))
      {
        availabilityOfUsers.add(true);
      }
      else
      {
        availabilityOfUsers.add(false);
      }  
    }  
  }
  
  /**
   * This method update chatRoom' messages and availability from database
   */
  public void updateMessage()
  {
    messages = new ArrayList<Message>();
    Document chat = collection.find(eq("_id",new ObjectId(dbId))).first();
    ArrayList<String> messageList = new ArrayList<String>();
    messageList = (ArrayList)chat.get("messages");
    for(int i= 0; i<messageList.size(); i++)
    {
      messages.add(new Message(messageList.get(i)));
    }
    ArrayList<String> availability = (ArrayList)chat.get("availability");
    availabilityOfUsers = new ArrayList<Boolean>();
    for(int i = 0; i<availability.size(); i++)
    {
      if(availability.get(i).equals("1"))
      {
        availabilityOfUsers.add(true);
      }
      else
      {
        availabilityOfUsers.add(false);
      } 
    }  
  } 
  
  /**
   * This constructor add messages to chatRoom
   * @param message which is given message
   */
  public void addMessage(Message message)
  {
    messages.add(message);
    String[] messageDb = new String[messages.size()];
    for(int i = 0; i<messages.size(); i++)
    {
      messageDb[i] = messages.get(i).getId();
    }
    //push to Db
    collection.updateOne(eq("_id",new ObjectId(dbId)),new Document("$set", new Document("messages", Arrays.asList(messageDb))));
  }
  
   /**
   * This constructor add user to chatRoom
   * @param user which is given user
   */
  public void addUser(User user)
  {
    users.add(user.getUsername());
    //Update database
  }
  
   /**
   * This method changes given user's availaiblity
   * @param user which is given user
   */
  public void changeAvailability(String user)
  {
    updateMessage();
    int index = users.indexOf(user);
    if(availabilityOfUsers.get(index) == false)
    {

      availabilityOfUsers.set(index, true);
    }
    else
    {

      availabilityOfUsers.set(index, false);
    }
    //Update Database
    String[] availableDb = new String[availabilityOfUsers.size()];
    for(int i =0; i<availableDb.length; i++)
    {
      if(availabilityOfUsers.get(i))
      {
        availableDb[i] = "1";
      }
      else
      {
        availableDb[i] = "0";
      }  
    }
    collection.updateOne(eq("_id",new ObjectId(dbId)),new Document("$set", new Document("availability", Arrays.asList(availableDb))));
  }
  
   /**
   * This method makes availability false of given user
   * @param user which is given user
   */
  public void setAvailableFalse(String user)
  {
    int index = users.indexOf(user);
    availabilityOfUsers.set(index, false);
    String[] availableDb = new String[availabilityOfUsers.size()];
    for(int i =0; i<availableDb.length; i++)
    {
      if(availabilityOfUsers.get(i))
      {
        availableDb[i] = "1";
      }
      else
      {
        availableDb[i] = "0";
      }  
    }
    collection.updateOne(eq("_id",new ObjectId(dbId)),new Document("$set", new Document("availability", Arrays.asList(availableDb))));
  }  
  
  //Get methods
   
  /**
   * This method gives chatRoom's messages
   * @return messages
   */
  public ArrayList<Message> getMessages()
  {
    return messages;
  }
   /**
   * This method gives chatRoom's users
   * @return users
   */
  public ArrayList<String> getUsers()
  {
    return users;
  }
   /**
   * This method gives chatRoom's availability list
   * @return availabilityOfUsers
   */
  public ArrayList<Boolean> getAvailabilityOfUsers()
  {
    return availabilityOfUsers;
  }
  
   /**
   * This method gives chatRoom's db ıd
   * @return dbId
   */
  public String getId()
  {
    return dbId;
  }
  
   /**
   * This method gives chatRoom's requests
   * @return requests
   */
  public ArrayList<Request> getRequests()
  {
    return requests;
  }  
}  
    
  
    