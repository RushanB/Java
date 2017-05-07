package B;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import A.CheckedForm;
import A.CheckedForm.Type;
import BasicIO.BinaryDataFile;
import BasicIO.BinaryOutputFile;

public class AcmeForm implements Serializable, Runnable{
 CheckedForm form; //instance variables
 JTextField date;
 JTextField time;
 JTextField order;
 JTextField item;
 JTextField quantity;
 JTextArea area;
 BinaryDataFile in;
 BinaryOutputFile out;
 private Queue queue;
 boolean start;

 public AcmeForm(Queue a){ //constructor method
  queue = a; 
  form = new CheckedForm("AcmeForm", Color.GRAY, 10,10); //Color 
  date = form.addField("Date",Type.DATE); //adds date
  time = form.addField("Time",Type.TIME); //adds time
  order = form.addField("Order #",Type.INTEGER); //adds order #
  item = form.addField("Item #",Type.INTEGER); //adds item #
  quantity = form.addField("Quantity",Type.INTEGER); //adds quantity amount
  area = form.addArea("Address"); //adds text field
 }
 @Override
 public void run(){ //executes AcmeForm continuously create request objects from parsed user input and insert into Queue
  start = true;
  System.out.println("******Acme Distribution Form******");
  A: while(true){
   form.resetForm(); //wait for user input
   while((!form.ok() && (!form.exit())) || form.ifEmpty()){
    if(form.exit()){
     form.setVisible(false);
     System.out.println("Thank you for using Acme Distribution Form");
     break A;
    }
   }
   String dat = date.getText(); //reads in form data
   String tim = time.getText();
   String ord = order.getText();
   String ite = item.getText();
   String qua = quantity.getText();
   String are = area.getText();
   Request req = new Request(dat, tim, ord, ite, qua, are); //request objects
   queue.add(req);
   form.reset();
  }
  start = false;
 }

 public CheckedForm getForm(){ //returns CheckedForm object for user input for AcmeForm
  return form;
 }
}