import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: Yavuz Faruk Bakman
 * @version: 24/12/2018
 * This program creates course data for program. It should be run once.
 */ 
public class StoreCourses
{
  public static void main (String[] args)
  {
     MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
     MongoClient mongoClient = new MongoClient(connectionString);
     MongoDatabase database = mongoClient.getDatabase("projectdb");
     MongoCollection<Document> collection = database.getCollection("Courses");
     Document doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 5 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 101)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 5 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 102)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 5 );
     collection.insertOne(doc);
     doc = new Document("Department", "CS")
                .append("courseCode", 201)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 5 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 101)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 5 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 102)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 401)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 401)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 401)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "ENG")
                .append("courseCode", 401)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 5 );
     collection.insertOne(doc);
      doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 8 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 9 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 101)
                .append("section", 10 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 5 );
     collection.insertOne(doc);
      doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 8 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 9 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 10 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 11 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 12 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 102)
                .append("section", 13 );
     collection.insertOne(doc);
      doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 5 );
     collection.insertOne(doc);
      doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 8 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 9 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 213)
                .append("section", 10 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 1 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 2 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 3 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 4 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 5 );
     collection.insertOne(doc);
      doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 6 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 7 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 8 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 9 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 10 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 11 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 12 );
     collection.insertOne(doc);
     doc = new Document("Department", "MATH")
                .append("courseCode", 132)
                .append("section", 13 );
     collection.insertOne(doc);
     
  }
}  
  
                
    