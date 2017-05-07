package C;

import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;
import B.*;

public class Test { //tests the buffer and opens AcmeForm until EOF and writes data to disk
 
 public Test(){
  Buffer buff = new Buffer(5); //set to 5
  HelpForm prod = new HelpForm(buff); //objects
  TechForm cons = new TechForm(buff);
  Thread pThread = new Thread(prod); //threads
  Thread cThread = new Thread(cons);
  prod.getForm().setThread(pThread); //thread passed to GUI for task handling for each thread
  cons.getForm().setThread(cThread);
  
  System.out.println("Loading Buffer");
  BinaryDataFile in = new BinaryDataFile();
  buff.populate(in); //reads in Buffers prev data
  pThread.start();
  cThread.start();
  while(pThread.isAlive() || cThread.isAlive()){ //run until end of file
   
  }
  System.out.println("Saving Data");
  BinaryOutputFile out = new BinaryOutputFile();
  buff.write(out); //prints out Buffers data
  System.exit(0);
 }
 
 public static void main(String args[]){ //run
  new Test();
 }

}
