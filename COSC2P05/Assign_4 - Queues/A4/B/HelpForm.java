package B;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;
import A.CheckedForm;
import A.Type;

public class HelpForm implements Serializable, Runnable{
 CheckedForm form; //instance variables
 JTextField date;
 JTextField time;
 JTextField number;
 JTextArea area;
 BinaryDataFile in;
 BinaryOutputFile out;
 private Buffer queue;
 boolean start;

 public HelpForm(Buffer a){ //constructor method
  queue = a; 
  form = new CheckedForm("HelpForm", Color.GRAY, 10,10); //Color 
  date = form.addField("Date",Type.DATE); //adds date
  time = form.addField("Time",Type.TIME); //adds time
  number = form.addField("Request #",Type.INTEGER); //adds request #
  area = form.addArea("Text"); //adds text field
 }
 @Override
 public void run(){ //executes HelpForm continuously create request objects from parsed user input and insert into buffer
  start = true;
  System.out.println("Starting Help Support Form");
  A: while(true){
   form.resetForm(); //wait for user input
   while((!form.ok() && (!form.exit())) || form.ifEmpty()){
    try{ //OK, then break and add items to queue otherwise exit
     Thread.sleep(4000);
     System.out.println("Please fill out the remaning fields in Help Form");
    }catch(InterruptedException e){

    }if(form.exit()){
     form.setVisible(false);
     System.out.println();
     System.out.println("Thank you for using the Help Form");
     break A;
    }
   }
   String dat = date.getText(); //reads in form data
   String tim = time.getText();
   String numb = number.getText();
   String are = area.getText();
   Request req = new Request(dat, tim, numb, are); //request objects
   queue.add(req);
   form.reset();
  }
  start = false;
 }

 public CheckedForm getForm(){ //returns CheckedForm object for user input for HelpForm
  return form;
 }
}
