package B;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;
import A.CheckedForm;
import A.Type;

public class TechForm implements Serializable, Runnable{
 CheckedForm form; //instance variables
 JTextField date;
 JTextField time;
 JTextField number;
 JTextArea area;
 BinaryDataFile in;
 BinaryOutputFile bin;
 private Buffer queue;
 boolean start;

 public TechForm(Buffer a){ //buffer method
  queue = a;
  form = new CheckedForm("TechForm", Color.BLUE, 800, 10); //Color
  date = form.addField("Date", A.Type.DATE); //adds date
  time = form.addField("Time", A.Type.TIME); //adds time 
  number = form.addField("Request #", A.Type.INTEGER); //adds request #
  area = form.addArea("Text"); //adds text field
  date.setEditable(false);
  time.setEditable(false);
  number.setEditable(false);
  area.setEditable(false);
 }

 @Override
 public void run() { //requests and displays objects
  start  = true; 
  System.out.println("Starting Tech Support Form");
  A: while(true){
   Request stored = queue.leave(); //blocked
   if(!form.exit()){ //display screen
    date.setText(stored.date);
    date.setForeground(Color.BLACK); //change
    time.setText(stored.time);
    number.setText(stored.rNumber);
    area.setText(stored.area);
   }
   form.resetForm(); //wait for user to continue
   form.setInput(true); //assume all input is valid
   if(form.exit()){
    form.setVisible(false);
    System.out.println();
    System.out.println("Thank you for using the Tech Form");
    break A;
   }
   while(!form.ok() && !form.exit()){ //add items to queue else exit form
    try {
     Thread.sleep(4000);
     System.out.println("Please fill out the remaining fields in Tech Form");
    } catch (InterruptedException e) {  
    }
    if(form.exit()){
     form.setVisible(false);
     System.out.println();
     System.out.println("Thank you for using the Tech Form");
     break A;
    }
   }
   form.reset();
  }
  start = false; //loop ends here
 }

 public CheckedForm getForm(){ //returns CheckedForm object for user input for HelpForm
  return form;
 }
}