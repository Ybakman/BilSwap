
/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 */ 
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Arrays;
import org.bson.types.*;
import static com.mongodb.client.model.Filters.*;
import java.util.ArrayList;

public class User {
    
    //Declare Variables
    final int MAX_REQUEST = 3;
    private String username;
    private String password;
    private String email;
    private ArrayList<Request> requests;
    private String dbId;
    private MongoDatabase db;
    private MongoCollection<Document> collection;
    
 /**
   *This contructor creates a new user by using db id
   *@param id 
   */
    User(String id) {
        db = DbAction.database;
        collection = db.getCollection("Users");
        // Get From Database
        dbId = id;
        ObjectId objectId = new ObjectId(id);
        Document user = collection.find(eq("_id", objectId)).first();
        this.username = user.get("username").toString();
        this.password = user.get("password").toString();
        this.email = user.get("email").toString();
        requests = new ArrayList<Request>();

        //Take ıd create request and add list
        for (int i = 0; i < ((ArrayList) user.get("requests")).size(); i++) {
            requests.add(new Request(((String) ((ArrayList) user.get("requests")).get(i)), this));
        }
    }
 /**
   *This contructor creates a new user and push it to db
   *@param username
   *@param password
   *@param email 
   */
    User(String username, String password, String email) {
        requests = new ArrayList<Request>();
        db = DbAction.database;
        //Create new one add to database
        // Check its property in panel action listener
        this.username = username;
        this.password = password;
        this.email = email;
        collection = db.getCollection("Users");
        Document newUser = new Document("username", this.username)
                .append("password", this.password)
                .append("email", this.email)
                .append("requests", Arrays.asList(new String[0]));
        collection.insertOne(newUser);
        ObjectId id = (ObjectId) newUser.get("_id");
        dbId = id.toString();
    }

    //UPDATE FROM DB
  /**
   *This method updates the user from db
   */
    public void updateUser() {
        ObjectId objectId = new ObjectId(dbId);
        Document user = collection.find(eq("_id", objectId)).first();

        requests = new ArrayList<Request>();
        //Take ıd create request and add list
        for (int i = 0; i < ((ArrayList) user.get("requests")).size(); i++) {
            requests.add(new Request(((String) ((ArrayList) user.get("requests")).get(i)), this));
        }
    }
     
 /**
   *This method add request to the user and update db
   *@param request
   */
    public void addRequest(Request request) {
        if (requests.size() <= MAX_REQUEST) {
            requests.add(request);
        }
        //Update Database
        String[] current = new String[requests.size()];
        //Store request's ID
        for (int i = 0; i < requests.size(); i++) {
            current[i] = requests.get(i).getId();
        }
        collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("requests", Arrays.asList(current))));//yer değiştirdik
        //First get current Data (don t  need :D)
    }
  /**
   *This method remove the request from the user and update db
   *@param request
   */
    public void removeRequest(Request request) {

        int index = requests.indexOf(request);

        requests.remove(index);
        // Update Database
        String[] current = new String[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            current[i] = requests.get(i).getId();
        }
        collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("requests", Arrays.asList(current))));//yer değiştirdik
    }

    //Set methods
    
  /**
   *This method sets the username and update db
   *@param username
   */
    public void setUsername(String username) {
        this.username = username;
        //Update Database
        collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("username", this.username)));
    }
    /**
   *This method sets the password and update db
   *@param password
   */
    public void setpassword(String password) {
        this.password = password;
        // Update Database
        collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("password", this.password)));
    }
 /**
   *This method sets the email and update db
   *@param email
   */
    public void setEmail(String email) {
        this.email = email;
        // Update Database
        collection.updateOne(eq("_id", new ObjectId(dbId)), new Document("$set", new Document("email", this.email)));
    }

    //Get methods
    
  /**
   *This method gives the user's requests
   *@return requests
   */
    public ArrayList<Request> getRequests() {
        return requests;
    }
 /**
   *This method gives the user's id
   *@return dbId
   */
    public String getId() {
        return dbId;
    }
  /**
   *This method gives the user's username
   *@return username
   */
    public String getUsername() {
        return username;
    }
  /**
   *This method gives the user's password
   *@return password
   */
    public String getPassword() {
        return password;
    }
 /**
   *This method gives the user's mail
   *@return email
   */
    public String getEmail() {
        return email;
    }
}
