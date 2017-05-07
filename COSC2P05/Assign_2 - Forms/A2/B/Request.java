package B;

import A.CheckedForm;
import A.Type;
import BasicIO.ASCIIDataFile;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class Request { //Test for Part B reads in queue and adds values to the queue 
 Queue<Value> queue; //queue for testing
 ASCIIDataFile file; //output file
 CheckedForm form; //form

 public Request() { //creates CheckedForm
  this.form = new CheckedForm();
  this.queue = new LinkedList<Value>();

  form.addField("date", "Date", Type.DATE);
  form.addField("time", "Time", Type.TIME);
  form.addField("order", "Value #", Type.INTEGER);
  form.addField("item", "Item #", Type.STRING);
  form.addField("quantity", "Quantity", Type.INTEGER);
  form.addText("address", "Text");
 }

 public void run() { //runs the request, checks if Accept or Quit
  readQ();

  while (true) {
   int button = form.accept("OK", "Quit");
   if (button == 0) {
    acceptButton();

   } else {
    printQ();
    System.exit(0);
   }
  }
 }

 private void printQ() { //prints out the queue
  for (Value s : queue) {
   s.printValue();
   System.out.println();
  }
 }

 private void readQ() { //reads in datafile to queue
  this.file = new ASCIIDataFile();
  while (!file.isEOF()) {
   try { //check if error
    String s = Double.toString(file.readDouble());
    System.out.println(s);
   }catch (Exception e){
    System.out.println("Error, unable to read file!");
    this.file = new ASCIIDataFile();
   }

  }
 }

 private void acceptButton() {  //error catch and check for overflow
  Date date = null;
  Date time = null;
  Number order = null;
  String item = null;
  Number quantity = null;
  try { //
   date = (Date) form.readField("date"); 
   time = (Date) form.readField("time");
   order = (Number) form.readField("order");
   item = (String) form.readField("item");
   quantity = (Number) form.readField("quantity");
  } catch (ClassCastException e) {
   e.printStackTrace();
  }
  String address = form.readText("address"); 
  form.clear("address");
  if (address != null) {
   form.writeString("address", address);
  }
  if (new Value(date, time, order, item, quantity, address)
    .getValue()) {
   try {
    queue.add(new Value(date, time, order, item, quantity,
      address));
    System.out.println(queue.size());
   } catch (StackOverflowError e) {
    System.exit(0);
   }

  }
 }

 public static void main(String[] args) { //run 
  new Request().run();
 }
}