package B;

import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;
import A.*;

public class Test { //tests the Queue and opens AcmeForm until EOF and writes data to disk
 
 public Test(){
  List q = new List(5); //list array size initialized to 5 for each
  AcmeForm distributer = new AcmeForm(q); //object
  
  Thread dThread = new Thread(distributer); //thread
  distributer.getForm().setThread(dThread); //thread passed to GUI for task handling
  
  System.out.println("Loading Data");
  BinaryDataFile in = new BinaryDataFile();
  q.populate(in); //reads in Queue prev data
  dThread.start();
  while(dThread.isAlive()){ //run until end of file
   
  }
  System.out.println("Saving Data");
  BinaryOutputFile out = new BinaryOutputFile();
  q.write(out); //prints out Queue data
  System.exit(0);
 }
 
 public static void main(String args[]){ //run
  new Test();
 }

}