package B;

import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;
import java.io.Serializable; 
import A.*;

public class List implements Queue, Serializable{
 private int first; //instance variables
 private int last;
 private int count;
 private Request[] array;
 private final int MAX;
 
 public List(int size){ //constructor method
  MAX = size;
  array = new Request[MAX];
  first = 0;
  last = 0;
  count = 0;
 }
 
 public synchronized void add(Request req){ //adds requested object to the queue
  try{
   while(count == MAX){
    System.out.println("Add: ");
    wait(); //if queue is full
    System.out.println("Acme Distribution Form could not add, invalid method");
   }
   array[last] = req;
   last = (last+1) % MAX;
   count++;
   notifyAll();
   System.out.print("Added to the List: " + count + "\n");
  }
  catch(InterruptedException e){
   System.out.println("Could not add to the List, invalid interrupt");
  }
 }
 
 public synchronized Request leave(){ //removes requested object from the queue
  Request a = null;
  try{
   while(count == 0){
    System.out.println("Leave: ");
    wait();
    System.out.println("Acme Distribution Form could not leave, invalid method");
   }
   System.out.println("Removing from the queue");
   a = array[first];
   first = (first+1) % MAX;
   count--;
   notifyAll();
   System.out.println("Removed from the List: " + count + "\n");
  }catch(InterruptedException e){
   System.out.println("Could not remove from the List, invalid interrupt");
  }
  return a;
 }
 
 public int length(){ //returns count of the queue
  return count;
 }
 public boolean isEmpty(){ //returns 0 making queue empty
  return count == 0;
 }
 
 public void populate(BinaryDataFile in){ //populates the queue read from binary data file
  Request tempReq = null;
  A: while(true){
   System.out.println("Processing Form");
  try{ 
  tempReq = (Request) in.readObject();
                if(in.isDataError()){  //incorrect data file 
                    break A;
                }
  System.out.println("Reading in object #: " + tempReq.item);
  } catch (NullPointerException e){  //EOF
   System.out.println("You have reached end of the file");
   break A;
  }
  array[last] = tempReq;
  last = (last+1) % MAX;
  count++;
  }
 }
 
 public void write(BinaryOutputFile out){ //writes the request from queue to DataFile
  while(array.length != 0){
   Request a = array[first];
   first = (first+1) % MAX;
   count--;
   out.writeObject(a); //write 
  }
 }
}
