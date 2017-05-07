package B;

import java.util.Date;

public class Value { //creates object value to add to queue for AcmeForm
 private boolean valid;
 private Date date;
 private Date time;
 private Number order;
 private String item;
 private Number quantity;
 private String address;

 public Value(Date date, Date time, Number order, String item, Number quantity, String address) { //creates order object 
  if (date == null || time == null || order == null || item == null|| quantity == null || address == null) {
   this.valid = false;
   System.out.println("Error, invalid value!");
   }else{
   this.valid = true;
   this.date = date;
   this.time = time;
   this.order = order;
   this.item = item;
   this.quantity = quantity;
   this.address = address;
   System.out.println("Process completed!");
  }
 }

 public boolean getValue() { //returns if fields are checked
  return this.valid;
 }

 public Date getDate() { //returns date
  return this.date;
 }

 public Date getTime() { //returns time
  return this.time;
 }

 public Number getOrder() { //returns order
  return this.order;
 }

 public String getItem() { //returns item
  return this.item;
 }

 public Number getQuantity() { //returns quantity
  return this.quantity;
 }

 public String getAddress() { //returns address
  return this.address;
 }

 public void printValue() { //prints values inside queue
  System.out.println(getDate() + "\t" + getTime() + "\t" + getOrder()+ "\t" + getItem() + "\t" + getQuantity() + "\t"+ getAddress() + "\t");
 }

}